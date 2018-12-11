package com.nb.module.nb.customer.api.partnerauthorization.controller;

import com.nb.module.nb.customer.api.partnerauthorization.biz.IPartnerAuthorizationService;
import com.nb.module.nb.customer.api.partnerauthorization.domain.PartnerAuthorization;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/partner")
public class PartnerAuthorizationController extends BaseController {

	@Autowired
	private IPartnerAuthorizationService service;

	@ApiOperation(value = "认证列表", notes = "认证列表")
	@RequestMapping(value = "/authorization/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<PartnerAuthorization>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "保存", notes = "保存")
	@RequestMapping(value = "/authorization/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated PartnerAuthorization partnerAuthorization) {
		service.save(partnerAuthorization);
		return setSuccessMessage();
	}
}
