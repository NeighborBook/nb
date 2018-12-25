package com.nb.module.nb.customer.base.userlocation.biz.impl;

import com.nb.module.nb.customer.base.userlocation.biz.ITNBUserLocationService;
import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.nb.module.nb.customer.base.userlocation.repository.ITNBUserLocationRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBUserLocationServiceImpl extends DataServiceImpl<TNBUserLocation, Integer> implements ITNBUserLocationService {

	@Autowired
	private ITNBUserLocationRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBUserLocation> findAllByUserCode(String userCode) {
		return repository.findAllByUserCode(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserLocation findOneByUserCodeAndLbsIdAndTagCode(String userCode, String lbsId, String tagCode) {
		return repository.findOneByUserCodeAndLbsIdAndTagCode(userCode, lbsId, tagCode);
	}
}
