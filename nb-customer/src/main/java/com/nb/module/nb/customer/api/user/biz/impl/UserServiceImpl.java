package com.nb.module.nb.customer.api.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.zjk.module.common.authorization.client.api.passport.client.IPassportClient;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private IPassportClient passportClient;

	@Override
	public User login(String username, String password) {
		return checkJsonContainer(passportClient.login(username, password));
	}

	@Override
	@Transactional
	public void updateLastLogin(String userCode) {
		checkJsonContainer(passportClient.updateLastLogin(userCode));
	}

}
