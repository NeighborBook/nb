package com.nb.module.nb.customer.api.login.biz.impl;

import com.nb.module.nb.customer.api.login.biz.ILoginService;
import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl extends CommonServiceImpl implements ILoginService {

	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;

	@Autowired
	private IUserService userService;

	@Override
	@Transactional
	public LoginResult login(String username, String password, HttpServletResponse response) {
		// 登录
		User user = userService.login(username, password);
		// 获取登录信息
		LoginResult login = getLoginResult(user);
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 写入头参数
		setHeaders(user, response);
		return login;
	}

	private void setHeaders(User user, HttpServletResponse response) {
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
	}

	private LoginResult getLoginResult(User user) {
		LoginResult login = new LoginResult();
		// 用户编号
		login.setCode(user.getCode());
		// 邮箱
		login.setEmail(user.getEmail());
		// 手机
		login.setMobile(user.getMobile());
		if (null != user.getSettings()) {
			// 语言
			login.setLang(user.getSettings().getLang());
			// 境内境外
			login.setInternational(user.getSettings().getInternational());
		}
		return login;
	}

}
