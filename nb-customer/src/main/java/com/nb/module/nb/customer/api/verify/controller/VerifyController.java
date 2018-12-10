package com.nb.module.nb.customer.api.verify.controller;


import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
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
@RequestMapping("/api/verify")
public class VerifyController extends BaseController {

	@Autowired
	private IVerifyService service;

	@ApiOperation(value = "验证短信", notes = "验证短信")
	@RequestMapping(value = "/sms", method = RequestMethod.POST)
	public JsonContainer<String> sms(@RequestParam @NotBlank String mobile, @RequestHeader(required = false) String lang) {
		return setSuccessMessage(service.sms(mobile, lang));
	}
}
