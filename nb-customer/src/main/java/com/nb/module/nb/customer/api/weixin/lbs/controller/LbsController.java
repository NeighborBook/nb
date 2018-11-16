package com.nb.module.nb.customer.api.weixin.lbs.controller;

import com.nb.module.nb.customer.api.weixin.lbs.biz.ILbsService;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;
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

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/lbs")
public class LbsController extends BaseController {

	@Autowired
	private ILbsService service;

	@ApiOperation(value = "获取地理位置", notes = "获取地理位置")
	@RequestMapping(value = "/suggestion", method = RequestMethod.GET)
	public JsonContainer<SuggestResult> suggestion(@RequestParam String keyword, @RequestParam String key) {
		return setSuccessMessage(service.suggestion(keyword, key));
	}

}
