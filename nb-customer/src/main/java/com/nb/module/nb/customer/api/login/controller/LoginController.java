package com.nb.module.nb.customer.api.login.controller;

import com.nb.module.nb.customer.api.login.biz.ILoginService;
import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

	@Autowired
	private ILoginService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonContainer<LoginResult> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password, HttpServletResponse response) {
		return setSuccessMessage(service.login(username, password, response));
	}
}
