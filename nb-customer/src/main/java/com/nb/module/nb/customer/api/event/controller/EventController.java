package com.nb.module.nb.customer.api.event.controller;

import com.nb.module.nb.customer.api.event.biz.IEventService;
import com.nb.module.nb.customer.api.event.domain.Event;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/event")
public class EventController extends BaseController {

	@Autowired
	private IEventService service;

	@ApiOperation(value = "保存活动", notes = "保存活动")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody Event event) {
		service.save(event);
		return setSuccessMessage();
	}

	@ApiOperation(value = "全部活动", notes = "全部活动")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<Event>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "活动详情", notes = "活动详情")
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public JsonContainer<Event> findOneByCode(@PathVariable String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}
}
