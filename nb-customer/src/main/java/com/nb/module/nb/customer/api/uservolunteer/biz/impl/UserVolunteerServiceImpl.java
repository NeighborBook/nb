package com.nb.module.nb.customer.api.uservolunteer.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.uservolunteer.biz.IUserVolunteerService;
import com.nb.module.nb.customer.api.uservolunteer.domain.UserVolunteer;
import com.nb.module.nb.customer.base.uservolunteer.biz.ITNBUserVolunteerService;
import com.nb.module.nb.customer.base.uservolunteer.domain.TNBUserVolunteer;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserVolunteerServiceImpl extends CommonServiceImpl implements IUserVolunteerService {

	@Autowired
	private ITNBUserVolunteerService userVolunteerService;
	@Autowired
	private IUserService userService;

	@Override
	@Transactional
	public void save(UserVolunteer userVolunteer) {
		TNBUserVolunteer po = userVolunteerService.findOneByCode(userVolunteer.getCode());
		if (null == po) {
			po = new TNBUserVolunteer();
		}
		po.setCode(userVolunteer.getCode());
		userVolunteerService.save(po);
		userService.updateNameAndProfession(userVolunteer.getCode(), userVolunteer.getName(), userVolunteer.getProfession());
	}

	private UserVolunteer convert(TNBUserVolunteer e) {
		User user = userService.findOneByCode(e.getCode(), null);
		return new UserVolunteer(e.getCode(), user.getSettings().getName(), user.getSettings().getProfession());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserVolunteer findOneByCode(String code) {
		return mapOneIfNotNull(userVolunteerService.findOneByCode(code), e -> convert(e));
	}

}
