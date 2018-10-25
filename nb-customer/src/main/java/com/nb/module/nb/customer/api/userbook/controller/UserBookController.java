package com.nb.module.nb.customer.api.userbook.controller;

import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/userbook")
public class UserBookController extends BaseController {

	@Autowired
	private IUserBookService service;

	@ApiOperation(value = "通过userCode和bookCode获取用户图书信息", notes = "通过userCode和bookCode获取用户图书信息")
	@RequestMapping(value = "/findOneByUserCodeAndBookCode", method = RequestMethod.GET)
	public JsonContainer<UserBook> findOneByUserCodeAndBookCode(@RequestParam @NotBlank String userCode, @RequestParam @NotBlank String bookCode) {
		return setSuccessMessage(service.findOneByUserCodeAndBookCode(userCode, bookCode));
	}

	@ApiOperation(value = "保存用户图书关系", notes = "保存用户图书关系")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated UserBook userBook) {
		service.save(userBook);
		return setSuccessMessage();
	}

}