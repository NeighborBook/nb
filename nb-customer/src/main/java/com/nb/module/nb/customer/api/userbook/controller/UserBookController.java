package com.nb.module.nb.customer.api.userbook.controller;

import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

	@ApiOperation(value = "通过标签和用户编号分页查询", notes = "通过标签和用户编号分页查询")
	@RequestMapping(value = "/findAllBySearchAndUserCode", method = RequestMethod.POST)
	public JsonContainer<Page<UserBookMinInfo>> findAllByTagCodeAndUserCode(@RequestBody @NotNull List<String> tagCodes, @RequestParam(required = false) Integer sharable, @RequestParam @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByTagCodeAndUserCode(tagCodes, sharable, userCode, pageable));
	}

	@ApiOperation(value = "通过搜索框和用户编号分页查询", notes = "通过搜索框和用户编号分页查询")
	@RequestMapping(value = "/findAllBySearchAndUserCode", method = RequestMethod.POST)
	public JsonContainer<Page<UserBookMinInfo>> findAllBySearchAndUserCode(@RequestParam @NotNull String search, @RequestParam @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllBySearchAndUserCode(search, userCode, pageable));
	}
}
