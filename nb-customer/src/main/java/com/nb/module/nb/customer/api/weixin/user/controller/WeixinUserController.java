package com.nb.module.nb.customer.api.weixin.user.controller;

import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/user")
public class WeixinUserController extends BaseController {

	@Autowired
	private IWeixinUserService service;

	@ApiOperation(value = "获取用户信息", notes = "获取用户信息")
	@RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
	public JsonContainer<User> findOneByCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findOneByCode(userCode));
	}

	@ApiOperation(value = "更新手机号", notes = "更新手机号")
	@RequestMapping(value = "/updateMobile", method = RequestMethod.PUT)
	public JsonContainer<User> updateMobile(@RequestBody @Validated Mobile mobile) {
		return setSuccessMessage(service.updateMobile(mobile));
	}

}
