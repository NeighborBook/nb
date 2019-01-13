package com.nb.module.nb.customer.api.userchildren.controller;

import com.nb.module.nb.customer.api.userchildren.biz.IUserChildrenService;
import com.nb.module.nb.customer.api.userchildren.domain.UserChildren;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/user/children")
public class UserChildrenController extends BaseController {

	@Autowired
	private IUserChildrenService service;

	@ApiOperation(value = "添加或修改孩子", notes = "添加或修改孩子")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody UserChildren userChildren) {
		service.save(userChildren);
		return setSuccessMessage();
	}

	@ApiOperation(value = "全部孩子", notes = "全部孩子")
	@RequestMapping(value = "/findAllByUserCode/{userCode}", method = RequestMethod.GET)
	public JsonContainer<List<UserChildren>> findAllByUserCode(@PathVariable String userCode) {
		return setSuccessMessage(service.findAllByUserCode(userCode));
	}

	@ApiOperation(value = "删除孩子", notes = "删除孩子")
	@RequestMapping(value = "/delete/{code}", method = RequestMethod.DELETE)
	public JsonContainer delete(@PathVariable String code) {
		service.delete(code);
		return setSuccessMessage();
	}
}
