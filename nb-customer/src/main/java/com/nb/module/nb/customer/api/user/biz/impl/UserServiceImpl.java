package com.nb.module.nb.customer.api.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.zjk.module.common.authorization.client.api.passport.client.IPassportCheckClient;
import com.zjk.module.common.authorization.client.api.passport.client.IPassportClient;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.role.client.IRoleClient;
import com.zjk.module.common.authorization.client.api.role.domain.Role;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.api.userrole.client.IUserRoleClient;
import com.zjk.module.common.authorization.client.api.userrole.domain.UserRole;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private IPassportClient passportClient;
	@Autowired
	private IPassportCheckClient passportCheckClient;
	@Autowired
	private IUserRoleClient userRoleClient;
	@Autowired
	private IRoleClient roleClient;

	@Override
	public User login(String username, String password) {
		return checkJsonContainer(passportClient.login(username, password));
	}

	@Override
	public User loginSimple(String username, String plugin) {
		return checkJsonContainer(passportClient.loginSimple(username, plugin));
	}

	@Override
	@Transactional
	public void updateLastLogin(String userCode) {
		checkJsonContainer(passportClient.updateLastLogin(userCode));
	}

	@Override
	@Transactional
	public User register(Register register, String plugin) {
		return checkJsonContainer(passportClient.register(register, plugin));
	}

	@Override
	public void existCode(String userCode) {
		checkJsonContainer(passportCheckClient.existCode(userCode));
	}

	@Override
	public void isNotExistMobile(String mobile) {
		checkJsonContainer(passportCheckClient.isNotExistMobile(mobile));
	}

	@Override
	public User findOneByCode(String userCode, String plugin) {
		return checkJsonContainer(passportClient.findOneByCode(userCode, plugin));
	}

	@Override
	public User findOneByUsername(String username, String plugin) {
		return checkJsonContainer(passportClient.findOneByUsername(username, plugin));
	}

	@Override
	@Transactional
	public void updateNameAndIdCard(String userCode, String userName, String idCard, Integer idCardType) {
		checkJsonContainer(passportClient.updateNameAndIdCard(userCode, userName, idCard, idCardType));
	}

	@Override
	@Transactional
	public void updateName(String userCode, String userName) {
		checkJsonContainer(passportClient.updateName(userCode, userName));
	}

	@Override
	@Transactional
	public void updateNameAndProfession(String userCode, String userName, String profession) {
		checkJsonContainer(passportClient.updateNameAndProfession(userCode, userName, profession));
	}

	@Override
	@Transactional
	public User updateUser(User user, String plugin) {
		return checkJsonContainer(passportClient.updateUser(user, plugin));
	}

	@Override
	public Role findRoleByRole(String role) {
		return checkJsonContainer(roleClient.findOneByRole(role));
	}

	@Override
	@Transactional
	public void saveUserRole(String userCode, String roleCode) {
		List<UserRole> vos = new ArrayList<>();
		vos.add(new UserRole(userCode, roleCode));
		saveUserRole(vos);
	}

	@Override
	@Transactional
	public void saveUserRole(List<UserRole> vos) {
		checkJsonContainer(userRoleClient.save(vos));
	}


	@Override
	@Transactional
	public void deleteByCode(String code, String plugin) {
		checkJsonContainer(passportClient.deleteByCode(code, plugin));
	}
}
