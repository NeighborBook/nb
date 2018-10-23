package com.nb.module.nb.customer.api.weixin.login.biz;


import com.nb.module.nb.customer.api.login.domain.LoginResult;

import javax.servlet.http.HttpServletResponse;

public interface IWeixinLoginService {

	/**
	 * 微信登录
	 *
	 * @param code
	 * @param response
	 * @return
	 */
	LoginResult login(String code, HttpServletResponse response);
}
