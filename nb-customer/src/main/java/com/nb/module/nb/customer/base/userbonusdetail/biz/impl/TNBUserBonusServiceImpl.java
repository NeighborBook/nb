package com.nb.module.nb.customer.base.userbonusdetail.biz.impl;

import com.nb.module.nb.customer.base.userbonusdetail.biz.ITNBUserBonusService;
import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.nb.module.nb.customer.base.userbonusdetail.repository.ITNBUserBonusDetailRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserBonusServiceImpl extends DataServiceImpl<TNBUserBonusDetail, Integer> implements ITNBUserBonusService {

	@Autowired
	private ITNBUserBonusDetailRepository repository;


	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserBonusDetail> findOneByUserCode(String userCode, Pageable pageable) {
		return repository.findOneByUserCode(userCode, pageable);
	}
}
