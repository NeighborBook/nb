package com.nb.module.nb.customer.api.userevent.controller;

import com.nb.module.nb.customer.api.userevent.biz.IUserEventService;
import com.nb.module.nb.customer.api.userevent.domain.UserEvent;
import com.nb.module.nb.customer.api.userevent.domain.UserEventDetail;
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
@RequestMapping("/api/userevent")
public class UserEventController extends BaseController {

	@Autowired
	private IUserEventService service;

	@ApiOperation(value = "报名", notes = "报名")
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public JsonContainer signUp(@RequestBody @Validated UserEvent userEvent) {
		service.signUp(userEvent);
		return setSuccessMessage();
	}

	@ApiOperation(value = "取消报名", notes = "取消报名")
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public JsonContainer cancel(@RequestBody @Validated UserEvent userEvent) {
		service.cancel(userEvent);
		return setSuccessMessage();
	}

	@ApiOperation(value = "用户活动详情", notes = "用户活动详情")
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public JsonContainer<UserEventDetail> detail(@RequestBody @Validated UserEvent userEvent) {
		return setSuccessMessage(service.detail(userEvent));
	}
}
