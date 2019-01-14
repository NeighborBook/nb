package com.nb.module.nb.customer.api.uservolunteer.biz.impl;

import com.nb.module.nb.customer.api.uservolunteer.biz.IUserVolunteerService;
import com.nb.module.nb.customer.api.uservolunteer.domain.UserVolunteer;
import com.nb.module.nb.customer.base.uservolunteer.biz.ITNBUserVolunteerService;
import com.nb.module.nb.customer.base.uservolunteer.domain.TNBUserVolunteer;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserVolunteerServiceImpl extends CommonServiceImpl implements IUserVolunteerService {

	@Autowired
	private ITNBUserVolunteerService userVolunteerService;

	@Override
	@Transactional
	public void save(UserVolunteer userVolunteer) {
		TNBUserVolunteer po = userVolunteerService.findOneByCode(userVolunteer.getCode());
		if (null == po) {
			po = new TNBUserVolunteer();
		}
		po.setCode(userVolunteer.getCode());
		po.setName(userVolunteer.getName());
		po.setProfession(userVolunteer.getProfession());
		userVolunteerService.save(po);
	}

	private UserVolunteer convert(TNBUserVolunteer e) {
		return new UserVolunteer(e.getCode(), e.getName(), e.getProfession());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserVolunteer findOneByCode(String code) {
		return mapOneIfNotNull(userVolunteerService.findOneByCode(code), e -> convert(e));
	}

}
