package com.nb.module.nb.customer.base.userbonus.biz.impl;

import com.nb.module.nb.customer.base.userbonus.biz.ITNBUserBonusService;
import com.nb.module.nb.customer.base.userbonus.domain.TNBUserBonus;
import com.nb.module.nb.customer.base.userbonus.repository.ITNBUserBonusRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserBonusServiceImpl extends DataServiceImpl<TNBUserBonus, Integer> implements ITNBUserBonusService {

	@Autowired
	private ITNBUserBonusRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserBonus findOneByUserCode(String userCode) {
		return repository.findOneByUserCode(userCode);
	}

}
