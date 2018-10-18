package com.nb.module.nb.customer.base.booktag.biz.impl;

import com.nb.module.nb.customer.base.booktag.biz.ITNBBookTagService;
import com.nb.module.nb.customer.base.booktag.domain.TNBBookTag;
import com.nb.module.nb.customer.base.booktag.repository.ITNBBookTagRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBBookTagServiceImpl extends DataServiceImpl<TNBBookTag, Integer> implements ITNBBookTagService {

	@Autowired
	private ITNBBookTagRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBBookTag findOneByBookCodeAndTagCode(String bookCode, String tagCode) {
		return repository.findOneByBookCodeAndTagCode(bookCode, tagCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBBookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode) {
		return repository.findAllByBookCodeOrderByTagCountDesc(bookCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBBookTag> findAllByTagCode(String tagCode) {
		return repository.findAllByTagCode(tagCode);
	}
}
