package com.nb.module.nb.customer.base.uservolunteer.biz.impl;

import com.nb.module.nb.customer.base.uservolunteer.biz.ITNBUserVolunteerService;
import com.nb.module.nb.customer.base.uservolunteer.domain.TNBUserVolunteer;
import com.nb.module.nb.customer.base.uservolunteer.repository.ITNBUserVolunteerRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TNBUserVolunteerServiceImpl extends DataServiceImpl<TNBUserVolunteer, Integer> implements ITNBUserVolunteerService {

	@Autowired
	private ITNBUserVolunteerRepository repository;

	@Override
	public TNBUserVolunteer findOneByCode(String code) {
		return repository.findOneByCode(code);
	}
}
