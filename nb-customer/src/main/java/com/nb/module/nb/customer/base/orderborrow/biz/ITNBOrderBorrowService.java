package com.nb.module.nb.customer.base.orderborrow.biz;


import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBOrderBorrowService extends IDataService<TNBOrderBorrow, Integer> {

	TNBOrderBorrow findOneByOrderCode(String code);

}
