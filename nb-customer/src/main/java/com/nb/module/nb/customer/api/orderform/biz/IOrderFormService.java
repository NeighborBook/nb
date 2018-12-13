package com.nb.module.nb.customer.api.orderform.biz;

import com.nb.module.nb.customer.api.orderform.domain.BorrowApply;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderFlow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderFormService {

	Page<OrderForm<OrderBorrow>> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable);

	Page<OrderForm<OrderBorrow>> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable);

	OrderForm<OrderBorrow> findOrderBorrowByOrderCode(String orderCode);

	List<OrderForm<OrderBorrow>> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus);

	List<OrderForm<OrderBorrow>>  findAllByUserCodeAndOrderTypeAndOrderStatus(String userCode, Integer orderType, Integer orderStatus);

	OrderForm<OrderBorrow> borrow(BorrowApply borrowApply);

	OrderForm<OrderBorrow> borrowFlow(OrderFlow orderFlow);

}
