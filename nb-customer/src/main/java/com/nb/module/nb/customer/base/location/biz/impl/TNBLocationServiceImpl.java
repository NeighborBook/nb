package com.nb.module.nb.customer.base.location.biz.impl;

import com.nb.module.nb.customer.base.location.biz.ITNBLocationService;
import com.nb.module.nb.customer.base.location.domain.TNBLocation;
import com.nb.module.nb.customer.base.location.repository.ITNBLocationRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBLocationServiceImpl extends DataServiceImpl<TNBLocation, Integer> implements ITNBLocationService {

	@Autowired
	private ITNBLocationRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBLocation findOneByLbsId(String lbsId) {
		return repository.findOneByLbsId(lbsId);
	}
}
