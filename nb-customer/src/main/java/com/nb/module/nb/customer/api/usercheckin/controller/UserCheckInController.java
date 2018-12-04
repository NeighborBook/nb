package com.nb.module.nb.customer.api.usercheckin.controller;

import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.usercheckin.biz.IUserCheckInService;
import com.nb.module.nb.customer.api.usercheckin.domain.UserCheckIn;
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
@RequestMapping("/api/usercheckin")
public class UserCheckInController extends BaseController {

	@Autowired
	private IUserCheckInService service;

	@ApiOperation(value = "签到", notes = "签到")
	@RequestMapping(value = "/checkIn", method = RequestMethod.POST)
	public JsonContainer<UserBonus> adjust(@RequestBody @Validated UserCheckIn userCheckIn) {
		return setSuccessMessage(service.checkIn(userCheckIn));
	}
}
