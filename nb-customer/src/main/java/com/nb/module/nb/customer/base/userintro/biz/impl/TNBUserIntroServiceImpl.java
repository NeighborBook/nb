package com.nb.module.nb.customer.base.userintro.biz.impl;

import com.nb.module.nb.customer.base.userintro.biz.ITNBUserIntroService;
import com.nb.module.nb.customer.base.userintro.domain.TNBUserIntro;
import com.nb.module.nb.customer.base.userintro.repository.ITNBUserIntroRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserIntroServiceImpl extends DataServiceImpl<TNBUserIntro, Integer> implements ITNBUserIntroService {

	@Autowired
	private ITNBUserIntroRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserIntro findOneByUserCode(String userCode) {
		return repository.findOneByUserCode(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserIntro> findAllByIntroUserCode(String introUserCode, Pageable pageable) {
		return repository.findAllByIntroUserCode(introUserCode, pageable);
	}

}
