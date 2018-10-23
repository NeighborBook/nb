package com.nb.module.nb.customer.api.weixin.login.controller;

import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.nb.module.nb.customer.api.weixin.login.biz.IWeixinLoginService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/login")
public class WeixinLoginController extends BaseController {

	@Autowired
	private IWeixinLoginService weixinLoginService;

	@ApiOperation(value = "微信登录", notes = "微信登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonContainer<LoginResult> login(@RequestParam @NotBlank String code, HttpServletResponse response) {
		return setSuccessMessage(weixinLoginService.login(code, response));
	}

}
