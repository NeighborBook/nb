package com.nb.module.nb.customer.base.orderborrow.biz.impl;

import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.nb.module.nb.customer.base.orderborrow.repository.ITNBOrderBorrowRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBOrderBorrowServiceImpl extends DataServiceImpl<TNBOrderBorrow, Integer> implements ITNBOrderBorrowService {

	@Autowired
	private ITNBOrderBorrowRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBOrderBorrow findOneByOrderCode(String code) {
		return repository.findOneByOrderCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBOrderBorrow> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus) {
		return repository.findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(ownerUserCode, bookCode, borrowerUserCode, orderStatus);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBOrderBorrow> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable) {
		return repository.findAllByOwnerUserCode(ownerUserCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBOrderBorrow> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable) {
		return repository.findAllByBorrowerUserCode(borrowerUserCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByOwnerUserCode(String ownerUserCode) {
		return repository.countByOwnerUserCode(ownerUserCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByBorrowerUserCode(String borrowerUserCode) {
		return repository.countByBorrowerUserCode(borrowerUserCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBOrderBorrow> findAllByOwnerUserCodeAndOrderStatus(String ownerUserCode, Integer orderStatus) {
		return repository.findAllByOwnerUserCodeAndOrderStatus(ownerUserCode, orderStatus);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBOrderBorrow> findAllByBorrowerUserCodeAndOrderStatus(String borrowerUserCode, Integer orderStatus) {
		return repository.findAllByBorrowerUserCodeAndOrderStatus(borrowerUserCode, orderStatus);
	}
}
