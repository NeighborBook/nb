package com.nb.module.nb.customer.api.orderform.biz;

import com.nb.module.nb.customer.api.orderform.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderFormService {

	UserBookAndOrderCount count(String userCode);

	UnfinishedOrderForms<OrderBorrow> findAllUnfinishedOrderFormsByOwnerUserCode(String ownerUserCode);

	UnfinishedOrderForms<OrderBorrow> findAllUnfinishedOrderFormsByBorrowerUserCode(String borrowerUserCode);

	Page<OrderForm<OrderBorrow>> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable);

	Page<OrderForm<OrderBorrow>> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable);

	OrderForm<OrderBorrow> findOrderBorrowByOrderCode(String orderCode);

	List<OrderForm<OrderBorrow>> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus);

	List<OrderForm<OrderBorrow>> findAllByUserCodeAndOrderTypeAndOrderStatus(String userCode, Integer orderType, Integer orderStatus);

	OrderForm<OrderBorrow> borrow(BorrowApply borrowApply);

	OrderForm<OrderBorrow> borrowFlow(OrderFlow orderFlow);

}
