package com.nb.module.nb.customer.api.orderform.biz.impl;

import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.constant.OrderFormConstant;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;
import com.nb.module.nb.customer.api.orderform.domain.OrderFormDetail;
import com.nb.module.nb.customer.api.orderform.exception.OrderFormCode;
import com.nb.module.nb.customer.api.weixin.message.biz.IMessageService;
import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.nb.module.nb.customer.base.orderform.biz.ITNBOrderFormService;
import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.nb.module.nb.customer.base.orderformdetail.biz.ITNBOrderFormDetailService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderForm<OrderBorrow>> findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(String fromUserCode, String bookCode, String toUserCode, Integer orderStatus) {
		return map(orderBorrowService.findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(fromUserCode, bookCode, toUserCode, orderStatus), e -> convert(e));
	}

	private OrderForm<OrderBorrow> convert(TNBOrderBorrow e) {
		TNBOrderForm orderFormPO = orderFormService.findOneByCode(e.getOrderCode());
		OrderForm<OrderBorrow> orderForm = new OrderForm<>(orderFormPO.getCreated(), orderFormPO.getUpdated(), orderFormPO.getCode(), orderFormPO.getOrderType(), orderFormPO.getOrderStatus());
		orderForm.setDetails(map(orderFormDetailService.findAllByCode(e.getOrderCode()), s -> new OrderFormDetail(s.getCreated(), s.getOrderDetailType(), s.getOrderDetailStatus())));
		orderForm.setOrder(mapOneIfNotNull(e, s -> new OrderBorrow(s.getFromUserCode(), s.getBookCode(), s.getToUserCode(), s.getCount(), s.getStartBorrowDate(), s.getInitialReturnDate(), s.getExpectedReturnDate(), s.getActualReturnDate())));
		return orderForm;
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrow(OrderBorrow orderBorrow) {
		OrderForm<OrderBorrow> orderForm;
		// 检查订单申请
		List<OrderForm<OrderBorrow>> list = findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(orderBorrow.getFromUserCode(), orderBorrow.getBookCode(), orderBorrow.getToUserCode(), OrderFormConstant.ORDER_STATUS_START);
		if (null == list || list.isEmpty()) {
			// 创建订单
			orderForm = new OrderForm<>(OrderFormConstant.ORDER_TYPE_BORROW, OrderFormConstant.ORDER_STATUS_START);
			orderForm.setOrder(orderBorrow);
			// 保存订单
			save(orderForm);
			// 发送消息
			weixinMessageService.sendBookLendingReminder("owzFy56rJumxnBhpdgl9iVLxDTlM", "马俊", "XXXXXXBOOK", new Date());
		} else {
			throw new BusinessException(OrderFormCode.OF0001, new Object[]{orderBorrow.getFromUserCode(), orderBorrow.getBookCode(), orderBorrow.getToUserCode()});
		}
		return orderForm;
	}

	private void save(OrderForm<OrderBorrow> orderForm) {
		TNBOrderForm orderFormPO = orderFormService.newInstance();


	}

}
