package com.nb.module.nb.customer.api.login.biz.impl;

import com.nb.module.nb.customer.api.login.biz.ILoginService;
import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl extends LoginCommonServiceImpl implements ILoginService {

	@Override
	@Transactional
	public LoginResult login(String username, String password, HttpServletResponse response) {
		// 登录
		User user = userService.login(username, password);
		return login(user, response);
	}

	protected LoginResult getLoginResult(User user) {
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
