package com.nb.module.nb.customer.api.orderform.biz;

import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;

public interface IOrderFormService {

	OrderForm<OrderBorrow> borrow(OrderBorrow orderBorrow);

}
