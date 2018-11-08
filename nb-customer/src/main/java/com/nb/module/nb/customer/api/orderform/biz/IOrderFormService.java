package com.nb.module.nb.customer.api.orderform.biz;

import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;

import java.util.List;

public interface IOrderFormService {

	List<OrderForm<OrderBorrow>> findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(String fromUserCode, String bookCode, String toUserCode, Integer orderStatus);

	OrderForm<OrderBorrow> borrow(OrderBorrow orderBorrow);

}
