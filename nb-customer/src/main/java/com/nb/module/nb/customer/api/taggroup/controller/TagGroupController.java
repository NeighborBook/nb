package com.nb.module.nb.customer.api.taggroup.controller;

import com.nb.module.nb.customer.api.taggroup.biz.ITagGroupService;
import com.nb.module.nb.customer.api.taggroup.domain.TagGroup;
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
@RequestMapping("/api/taggroup")
public class TagGroupController extends BaseController {

	@Autowired
	private ITagGroupService service;

	@ApiOperation(value = "保存标签组", notes = "保存标签组")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated TagGroup tagGroup) {
		service.save(tagGroup);
		return setSuccessMessage();
	}

	@ApiOperation(value = "保存标签组", notes = "保存标签组")
	@RequestMapping(value = "/findAllByVisible", method = RequestMethod.GET)
	public JsonContainer<List<TagGroup>> findAllByVisible(@RequestParam Integer visible) {
		return setSuccessMessage(service.findAllByVisible(visible));
	}

}
