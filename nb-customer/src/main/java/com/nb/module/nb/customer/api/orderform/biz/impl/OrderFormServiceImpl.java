package com.nb.module.nb.customer.api.orderform.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.constant.OrderFormConstant;
import com.nb.module.nb.customer.api.orderform.domain.BorrowApply;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;
import com.nb.module.nb.customer.api.orderform.domain.OrderFormDetail;
import com.nb.module.nb.customer.api.orderform.exception.OrderFormCode;
import com.nb.module.nb.customer.api.weixin.message.biz.IMessageService;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.nb.module.nb.customer.base.orderform.biz.ITNBOrderFormService;
import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.nb.module.nb.customer.base.orderformdetail.biz.ITNBOrderFormDetailService;
import com.nb.module.nb.customer.base.orderformdetail.domain.TNBOrderFormDetail;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Service
public class OrderFormServiceImpl extends CommonServiceImpl implements IOrderFormService {

	@Autowired
	private ITNBOrderFormService orderFormService;
	@Autowired
	private ITNBOrderFormDetailService orderFormDetailService;
	@Autowired
	private ITNBOrderBorrowService orderBorrowService;

	@Autowired
	private IMessageService weixinMessageService;
	@Autowired
	private IWeixinUserService weixinUserService;
	@Autowired
	private IBookService bookService;

	private OrderForm<OrderBorrow> convert(TNBOrderBorrow e) {
		TNBOrderForm orderFormPO = orderFormService.findOneByCode(e.getOrderCode());
		OrderForm<OrderBorrow> orderForm = new OrderForm<>(orderFormPO.getCreated(), orderFormPO.getUpdated(), orderFormPO.getCode(), orderFormPO.getOrderType(), orderFormPO.getOrderStatus());
		orderForm.setDetails(map(orderFormDetailService.findAllByCode(e.getOrderCode()), s -> new OrderFormDetail(s.getCreated(), s.getOrderDetailType(), s.getOrderDetailStatus(), s.getRemark())));
		orderForm.setOrder(mapOneIfNotNull(e, s -> new OrderBorrow(s.getFromUserCode(), s.getBookCode(), s.getToUserCode(), s.getBookCount(), s.getStartBorrowDate(), s.getInitialReturnDate(), s.getExpectedReturnDate(), s.getActualReturnDate())));
		return orderForm;
	}

	private OrderBorrow convert(BorrowApply borrowApply) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(borrowApply.getStartBorrowDate());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		Date initialReturnDate = calendar.getTime();
		return new OrderBorrow(borrowApply.getFromUserCode(), borrowApply.getBookCode(), borrowApply.getToUserCode(), borrowApply.getBookCount(), borrowApply.getStartBorrowDate(), initialReturnDate, initialReturnDate, null);
	}

	private TNBOrderBorrow convert(OrderForm<OrderBorrow> orderForm) {
		OrderBorrow orderBorrow = orderForm.getOrder();
		TNBOrderBorrow po = new TNBOrderBorrow();
		po.setOrderCode(orderForm.getCode());
		po.setFromUserCode(orderBorrow.getFromUserCode());
		po.setBookCode(orderBorrow.getBookCode());
		po.setToUserCode(orderBorrow.getToUserCode());
		po.setBookCount(orderBorrow.getBookCount());
		po.setStartBorrowDate(orderBorrow.getStartBorrowDate());
		po.setInitialReturnDate(orderBorrow.getInitialReturnDate());
		po.setExpectedReturnDate(orderBorrow.getExpectedReturnDate());
		po.setActualReturnDate(orderBorrow.getActualReturnDate());
		return po;
	}

	private <T> void save(OrderForm<T> orderForm, Consumer<OrderForm<T>> consumer) {
		// 订单主体
		TNBOrderForm orderFormPO = orderFormService.newInstance();
		orderFormPO.setOrderType(orderForm.getOrderType());
		orderFormPO.setOrderStatus(orderForm.getOrderStatus());
		orderFormService.save(orderFormPO);

		orderForm.setCreated(orderFormPO.getCreated());
		orderForm.setUpdated(orderFormPO.getUpdated());
		orderForm.setCode(orderFormPO.getCode());
		// 订单明细
		orderForm.getDetails().forEach(e -> {
			TNBOrderFormDetail detail = new TNBOrderFormDetail();
			detail.setCode(orderFormPO.getCode());
			detail.setOrderDetailType(e.getOrderDetailType());
			detail.setOrderDetailStatus(e.getOrderDetailStatus());
			detail.setRemark(e.getRemark());
			orderFormDetailService.save(detail);

			e.setCreated(detail.getCreated());
		});
		// 订单
		consumer.accept(orderForm);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderForm<OrderBorrow>> findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(String fromUserCode, String bookCode, String toUserCode, Integer orderStatus) {
		return map(orderBorrowService.findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(fromUserCode, bookCode, toUserCode, orderStatus), e -> convert(e));
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrow(BorrowApply borrowApply) {
		OrderForm<OrderBorrow> orderForm;
		// 检查订单申请
		List<OrderForm<OrderBorrow>> list = findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(borrowApply.getFromUserCode(), borrowApply.getBookCode(), borrowApply.getToUserCode(), OrderFormConstant.ORDER_STATUS_START);
		if (null == list || list.isEmpty()) {
			// 创建订单
			orderForm = new OrderForm<>(OrderFormConstant.ORDER_TYPE_BORROW, OrderFormConstant.ORDER_STATUS_START);
			orderForm.setOrder(convert(borrowApply));
			orderForm.getDetails().add(new OrderFormDetail(OrderFormConstant.ORDER_DETAIL_TYPE_START_BORROW_APPLICATION, OrderFormConstant.ORDER_DETAIL_STATUS_AGREE, borrowApply.getRemark()));
			// 保存订单
			save(orderForm, e -> orderBorrowService.save(convert(e)));
			// 发送消息
			sendBookLendingReminder(orderForm);
		} else {
			throw new BusinessException(OrderFormCode.OF0001, new Object[]{borrowApply.getFromUserCode(), borrowApply.getBookCode(), borrowApply.getToUserCode()});
		}
		return orderForm;
	}

	private void sendBookLendingReminder(OrderForm<OrderBorrow> orderForm) {
		weixinMessageService.sendBookLendingReminder(weixinUserService.findOpenidByCode(orderForm.getOrder().getToUserCode()),
				weixinUserService.findNicknameByCode(orderForm.getOrder().getFromUserCode()),
				mapOneIfNotNull(bookService.findOneByCode(orderForm.getOrder().getBookCode()), e -> e.getTitle()),
				orderForm.getOrder().getStartBorrowDate());
	}

}
