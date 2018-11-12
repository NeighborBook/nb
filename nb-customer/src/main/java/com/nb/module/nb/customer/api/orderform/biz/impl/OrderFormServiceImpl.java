package com.nb.module.nb.customer.api.orderform.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.constant.OrderFormConstant;
import com.nb.module.nb.customer.api.orderform.domain.*;
import com.nb.module.nb.customer.api.orderform.exception.OrderFormCode;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
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
	@Autowired
	private IUserBookService userBookService;

	private OrderForm<OrderBorrow> convert(TNBOrderBorrow e) {
		TNBOrderForm orderFormPO = orderFormService.findOneByCode(e.getOrderCode());
		OrderForm<OrderBorrow> orderForm = null;
		if (null != orderFormPO) {
			orderForm = new OrderForm<>(orderFormPO.getCreated(), orderFormPO.getUpdated(), orderFormPO.getCode(), orderFormPO.getOrderType(), orderFormPO.getOrderStatus());
			orderForm.setDetails(map(orderFormDetailService.findAllByCode(e.getOrderCode()), s -> new OrderFormDetail(s.getCreated(), s.getOrderDetailType(), s.getOrderDetailStatus(), s.getRemark())));
			orderForm.setOrder(mapOneIfNotNull(e, s -> new OrderBorrow(s.getOwnerUserCode(), s.getBookCode(), s.getBorrowerUserCode(), s.getBookCount(), s.getStartBorrowDate(), s.getInitialReturnDate(), s.getExpectedReturnDate(), s.getActualReturnDate())));
		}
		return orderForm;
	}

	private OrderBorrow convert(BorrowApply borrowApply) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(borrowApply.getStartBorrowDate());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		Date initialReturnDate = calendar.getTime();
		return new OrderBorrow(borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode(), borrowApply.getBookCount(), borrowApply.getStartBorrowDate(), initialReturnDate, initialReturnDate, null);
	}

	private TNBOrderBorrow convert(OrderForm<OrderBorrow> orderForm) {
		OrderBorrow orderBorrow = orderForm.getOrder();
		TNBOrderBorrow po = new TNBOrderBorrow();
		po.setOrderCode(orderForm.getCode());
		po.setOwnerUserCode(orderBorrow.getOwnerUserCode());
		po.setBookCode(orderBorrow.getBookCode());
		po.setBorrowerUserCode(orderBorrow.getBorrowerUserCode());
		po.setBookCount(orderBorrow.getBookCount());
		po.setStartBorrowDate(orderBorrow.getStartBorrowDate());
		po.setInitialReturnDate(orderBorrow.getInitialReturnDate());
		po.setExpectedReturnDate(orderBorrow.getExpectedReturnDate());
		po.setActualReturnDate(orderBorrow.getActualReturnDate());
		return po;
	}

	private <T> void save(OrderForm<T> orderForm, Consumer<OrderForm<T>> consumer) {
		// 订单主体
		TNBOrderForm po = orderFormService.newInstance();
		po.setOrderType(orderForm.getOrderType());
		po.setOrderStatus(orderForm.getOrderStatus());
		orderFormService.save(po);

		orderForm.setCreated(po.getCreated());
		orderForm.setUpdated(po.getUpdated());
		orderForm.setCode(po.getCode());
		// 订单明细
		orderForm.getDetails().forEach(e -> save(po.getCode(), e));
		// 订单
		consumer.accept(orderForm);
	}

	private void save(String orderCode, OrderFormDetail orderFormDetail) {
		TNBOrderFormDetail po = new TNBOrderFormDetail();
		po.setCode(orderCode);
		po.setOrderDetailType(orderFormDetail.getOrderDetailType());
		po.setOrderDetailStatus(orderFormDetail.getOrderDetailStatus());
		po.setRemark(orderFormDetail.getRemark());
		orderFormDetailService.save(po);

		orderFormDetail.setCreated(po.getCreated());
	}

	private void checkOrder(BorrowApply borrowApply) {
		UserBook userBook = userBookService.findOneByUserCodeAndBookCode(borrowApply.getOwnerUserCode(), borrowApply.getBookCode());
		// 没有这本书
		if (null == userBook) {
			throw new BusinessException(OrderFormCode.OF0002, new Object[]{borrowApply.getOwnerUserCode(), borrowApply.getBookCode()});
		}
		// 这本书没有库存拉
		if (userBook.getBookCount() <= userBook.getLentAmount()) {
			throw new BusinessException(OrderFormCode.OF0003, new Object[]{borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), userBook.getBookCount(), userBook.getLentAmount()});
		}
	}

	private void checkOrderBorrow(BorrowApply borrowApply) {
		checkOrder(borrowApply);
		List<OrderForm<OrderBorrow>> list = findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode(), OrderFormConstant.ORDER_STATUS_START);
		// 同一本书只能发起一次借书请求
		if (null != list && !list.isEmpty()) {
			throw new BusinessException(OrderFormCode.OF0001, new Object[]{borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode()});
		}
	}

	private void sendBookLendingReminder(OrderForm<OrderBorrow> orderForm) {
		weixinMessageService.sendBookLendingReminder(weixinUserService.findOpenidByCode(orderForm.getOrder().getOwnerUserCode()),
				weixinUserService.findNicknameByCode(orderForm.getOrder().getBorrowerUserCode()),
				mapOneIfNotNull(bookService.findOneByCode(orderForm.getOrder().getBookCode()), e -> e.getTitle()),
				orderForm.getOrder().getStartBorrowDate());
	}

	/*****************************************************************************************************************/

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public OrderForm<OrderBorrow> findOrderBorrowByOrderCode(String orderCode) {
		return mapOneIfNotNull(orderBorrowService.findOneByOrderCode(orderCode), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderForm<OrderBorrow>> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus) {
		return map(orderBorrowService.findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(ownerUserCode, bookCode, borrowerUserCode, orderStatus), e -> convert(e));
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrow(BorrowApply borrowApply) {
		OrderForm<OrderBorrow> orderForm;
		// 检查订单申请
		checkOrderBorrow(borrowApply);
		// 创建订单
		orderForm = new OrderForm<>(OrderFormConstant.ORDER_TYPE_BORROW, OrderFormConstant.ORDER_STATUS_START);
		orderForm.setOrder(convert(borrowApply));
		orderForm.getDetails().add(new OrderFormDetail(OrderFormConstant.ORDER_DETAIL_TYPE_START_BORROW_APPLICATION, OrderFormConstant.ORDER_DETAIL_STATUS_AGREE, borrowApply.getRemark()));
		// 保存订单
		save(orderForm, e -> orderBorrowService.save(convert(e)));
		// 发送消息
		sendBookLendingReminder(orderForm);
		return orderForm;
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrowFlow(OrderFlow orderFlow) {
		OrderForm<OrderBorrow> orderForm = findOrderBorrowByOrderCode(orderFlow.getOrderCode());
		// 订单不存在
		if (null == orderForm) {
			throw new BusinessException(OrderFormCode.OF0004, new Object[]{orderFlow.getOrderCode()});
		}
		// 借书流程
		switch (orderFlow.getOrderDetailType()) {
			// 确认借书申请
			case OrderFormConstant.ORDER_DETAIL_TYPE_CONFIRM_BORROW_APPLICATION:
				break;
			default:
				throw new BusinessException(OrderFormCode.OF0005, new Object[]{orderFlow.getOrderCode(), orderFlow.getOrderDetailType()});
		}
		return orderForm;
	}

}
