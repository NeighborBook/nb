package com.nb.module.nb.customer.api.usershare.controller;

import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.usershare.biz.IUserShareService;
import com.nb.module.nb.customer.api.usershare.domain.UserShare;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/share")
public class UserShareController extends BaseController {

	@Autowired
	private IUserShareService service;

	@ApiOperation(value = "分享", notes = "分享")
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public JsonContainer<UserBonus> share(@RequestBody @Validated UserShare userShare) {
		return setSuccessMessage(service.share(userShare));
	}
}
