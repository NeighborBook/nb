package com.nb.module.nb.customer.api.login.biz.impl;


import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

public abstract class LoginCommonServiceImpl extends CommonServiceImpl {

	@Autowired
	protected IJSONWebTokenClient jsonWebTokenClient;
	@Autowired
	protected IUserService userService;

	/**
	 * 获取登录信息
	 *
	 * @param user
	 * @return
	 */
	protected abstract LoginResult getLoginResult(User user);

	/**
	 * 登录
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	protected LoginResult login(User user, HttpServletResponse response) {
		// 获取登录信息
		LoginResult login = getLoginResult(user);
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 写入头参数
		setHeader(response, user);
		return login;
	}

	/**
	 * 写入头参数
	 *
	 * @param response
	 * @param user
	 */
	protected void setHeader(HttpServletResponse response, User user) {
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
	}
}
