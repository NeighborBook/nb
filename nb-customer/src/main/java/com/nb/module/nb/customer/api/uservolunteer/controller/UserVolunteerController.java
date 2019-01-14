package com.nb.module.nb.customer.api.uservolunteer.controller;

import com.nb.module.nb.customer.api.uservolunteer.biz.IUserVolunteerService;
import com.nb.module.nb.customer.api.uservolunteer.domain.UserVolunteer;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/user/volunteer")
public class UserVolunteerController extends BaseController {

	@Autowired
	private IUserVolunteerService service;

	@ApiOperation(value = "成为志愿者", notes = "成为志愿者")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated UserVolunteer userVolunteer) {
		service.save(userVolunteer);
		return setSuccessMessage();
	}

	@ApiOperation(value = "志愿者信息", notes = "志愿者信息")
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public JsonContainer<UserVolunteer> findOneByCode(@PathVariable String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}

}
