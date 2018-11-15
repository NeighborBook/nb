package com.nb.module.nb.customer.api.book.controller;

import com.nb.module.nb.customer.api.book.biz.IBookUpdateService;
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
@RequestMapping("/api/book/update")
public class BookUpdateController extends BaseController {

	@Autowired
	private IBookUpdateService service;

	@ApiOperation(value = "更新图片", notes = "更新图片")
	@RequestMapping(value = "/image", method = RequestMethod.PUT)
	public JsonContainer<String> updateImage(@RequestParam String code, @RequestParam String url) {
		return setSuccessMessage(service.updateImage(code, url));
	}

}
