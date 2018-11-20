package com.nb.module.nb.customer.api.user.biz;


import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.role.domain.Role;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.api.userrole.domain.UserRole;

import java.util.List;

public interface IUserService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * 登录简单版
	 *
	 * @param username
	 * @param plugin
	 * @return
	 */
	User loginSimple(String username, String plugin);

	/**
	 * 更新最后登录时间
	 *
	 * @param userCode
	 */
	void updateLastLogin(String userCode);

	/**
	 * 注册
	 *
	 * @param register
	 * @param plugin
	 * @return
	 */
	User register(Register register, String plugin);

	/**
	 * 校验用户编号存在
	 *
	 * @param userCode
	 */
	void existCode(String userCode);

	/**
	 * 校验手机号不存在
	 *
	 * @param mobile
	 */
	void isNotExistMobile(String mobile);

	/**
	 * 通过用户编号查询
	 *
	 * @param userCode
	 * @param plugin
	 * @return
	 */
	User findOneByCode(String userCode, String plugin);

	/**
	 * 更新用户姓名和证件信息
	 *
	 * @param userCode
	 * @param userName
	 * @param idCard
	 * @param idCardType
	 */
	void updateNameAndIdCard(String userCode, String userName, String idCard, Integer idCardType);

	/**
	 * 更新用户姓名
	 *
	 * @param userCode
	 * @param userName
	 */
	void updateName(String userCode, String userName);

	/**
	 * 更新用户信息
	 *
	 * @param user
	 * @param plugin
	 */
	User updateUser(User user, String plugin);

	/**
	 * 查询角色
	 *
	 * @param role
	 * @return
	 */
	Role findRoleByRole(String role);

	/**
	 * 绑定用户角色
	 *
	 * @param userCode
	 * @param roleCode
	 */
	void saveUserRole(String userCode, String roleCode);

	/**
	 * 绑定用户角色
	 *
	 * @param vos
	 */
	void saveUserRole(List<UserRole> vos);

	/**
	 * 删除
	 *
	 * @param code
	 * @param plugin
	 */
	void deleteByCode(String code, String plugin);
}
