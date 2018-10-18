package com.nb.module.nb.customer.api.user.biz;


import com.zjk.module.common.authorization.client.api.user.domain.User;

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
	 * 更新最后登录时间
	 *
	 * @param userCode
	 */
	void updateLastLogin(String userCode);
}
