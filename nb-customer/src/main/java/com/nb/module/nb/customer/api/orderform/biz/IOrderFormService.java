package com.nb.module.nb.customer.api.orderform.biz;

import com.nb.module.nb.customer.api.orderform.domain.BorrowApply;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderFlow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;

import java.util.List;

public interface IOrderFormService {

	List<OrderForm<OrderBorrow>> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus);

	OrderForm<OrderBorrow> borrow(BorrowApply borrowApply);

	OrderForm<OrderBorrow> borrowFlow(OrderFlow orderFlow);

}
