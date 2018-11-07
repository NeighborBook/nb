package com.nb.module.nb.customer.api.orderform.biz.impl;

import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;
import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderform.biz.ITNBOrderFormService;
import com.nb.module.nb.customer.base.orderformdetail.biz.ITNBOrderFormDetailService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFormServiceImpl extends CommonServiceImpl implements IOrderFormService {

	@Autowired
	private ITNBOrderFormService orderFormService;
	@Autowired
	private ITNBOrderFormDetailService orderFormDetailService;
	@Autowired
	private ITNBOrderBorrowService orderBorrowService;

	@Override
	public OrderForm<OrderBorrow> borrow(OrderBorrow orderBorrow) {
		return null;
	}
}
