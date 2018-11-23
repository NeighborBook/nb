package com.nb.module.nb.customer.base.userlocation.biz.impl;

import com.nb.module.nb.customer.base.userlocation.biz.ITNBUserLocationService;
import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.nb.module.nb.customer.base.userlocation.repository.ITNBUserLocationRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TNBUserLocationServiceImpl extends DataServiceImpl<TNBUserLocation, Integer> implements ITNBUserLocationService {

	@Autowired
	private ITNBUserLocationRepository repository;

	@Override
	public TNBUserLocation findOneByUserCode(String userCode) {
		return repository.findOneByUserCode(userCode);
	}
}
