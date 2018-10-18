package com.nb.module.nb.customer.api.login.biz;


import com.nb.module.nb.customer.api.login.domain.LoginResult;

import javax.servlet.http.HttpServletResponse;

public interface ILoginService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @param response
	 * @return
	 */
	LoginResult login(String username, String password, HttpServletResponse response);
}
