package com.nb.module.nb.customer.api.tag.controller;

import com.nb.module.nb.customer.api.tag.biz.ITagService;
import com.nb.module.nb.customer.api.tag.domain.BookTags;
import com.nb.module.nb.customer.api.tag.domain.Tag;
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
@RequestMapping("/api/tag")
public class TagController extends BaseController {

	@Autowired
	private ITagService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<Tag>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "保存标签", notes = "保存标签")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody Tag tag) {
		service.save(tag);
		return setSuccessMessage();
	}

	@ApiOperation(value = "绑定书标签", notes = "绑定书标签")
	@RequestMapping(value = "/saveBookTags", method = RequestMethod.POST)
	public JsonContainer saveBookTags(@RequestBody BookTags bookTags) {
		service.saveBookTags(bookTags);
		return setSuccessMessage();
	}

}
