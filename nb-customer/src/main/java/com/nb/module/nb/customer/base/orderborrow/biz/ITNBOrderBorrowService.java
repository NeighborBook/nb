package com.nb.module.nb.customer.base.orderborrow.biz;


import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITNBOrderBorrowService extends IDataService<TNBOrderBorrow, Integer> {

	TNBOrderBorrow findOneByOrderCode(String code);

	List<TNBOrderBorrow> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus);

	Page<TNBOrderBorrow> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable);

	Page<TNBOrderBorrow> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable);
}
