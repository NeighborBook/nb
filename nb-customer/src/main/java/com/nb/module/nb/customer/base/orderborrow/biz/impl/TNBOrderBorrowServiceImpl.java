package com.nb.module.nb.customer.base.orderborrow.biz.impl;

import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.nb.module.nb.customer.base.orderborrow.repository.ITNBOrderBorrowRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBOrderBorrowServiceImpl extends DataServiceImpl<TNBOrderBorrow, Integer> implements ITNBOrderBorrowService {

	@Autowired
	private ITNBOrderBorrowRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBOrderBorrow findOneByOrderCode(String code) {
		return repository.findOneByOrderCode(code);
	}

}
