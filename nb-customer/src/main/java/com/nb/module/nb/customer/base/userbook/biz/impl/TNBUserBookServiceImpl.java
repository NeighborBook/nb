package com.nb.module.nb.customer.base.userbook.biz.impl;

import com.nb.module.nb.customer.base.userbook.biz.ITNBUserBookService;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBookCount;
import com.nb.module.nb.customer.base.userbook.repository.ITNBUserBookRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBUserBookServiceImpl extends DataServiceImpl<TNBUserBook, Integer> implements ITNBUserBookService {

	@Autowired
	private ITNBUserBookRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserBook findOneByUserCodeAndBookCode(String userCode, String bookCode) {
		return repository.findOneByUserCodeAndBookCode(userCode, bookCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBUserBook> findAllByUserCodeOrderByBookCountDesc(String userCode) {
		return repository.findAllByUserCodeOrderByBookCountDesc(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserBook> findAllByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable) {
		return repository.findAllByBookCodeAndSharable(bookCode, sharable, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserBookCount> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable) {
		return repository.findAllByLbsIdInAndUserCodeNot(lbsId, userCode, pageable);
	}
}
