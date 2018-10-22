package com.nb.module.nb.customer.api.weixin.token.controller;

import com.nb.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;
import com.nb.module.partner.weixin.client.util.Sign;
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

import javax.validation.constraints.NotBlank;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/token")
public class TokenController extends BaseController {

	@Autowired
	private ITokenService service;

	@ApiOperation(value = "获取accessToken", notes = "获取accessToken")
	@RequestMapping(value = "/accessToken", method = RequestMethod.GET)
	public JsonContainer<AccessToken> accessToken() {
		return setSuccessMessage(service.accessToken());
	}

	@ApiOperation(value = "获取ticket", notes = "获取ticket")
	@RequestMapping(value = "/getTicket", method = RequestMethod.GET)
	public JsonContainer<JsapiTicket> getTicket() {
		return setSuccessMessage(service.getTicket());
	}

	@ApiOperation(value = "获取sign", notes = "获取sign")
	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public JsonContainer<Sign> sign(@RequestParam @NotBlank String url) {
		return setSuccessMessage(service.sign(url));
	}
}
