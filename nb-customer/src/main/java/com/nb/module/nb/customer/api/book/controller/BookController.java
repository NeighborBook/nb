package com.nb.module.nb.customer.api.book.controller;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/book")
public class BookController extends BaseController {

	@Autowired
	private IBookService service;

	@ApiOperation(value = "通过isbn获取图书", notes = "通过isbn获取图书")
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public JsonContainer<Book> findOneByISBN(@PathVariable String isbn) {
		return setSuccessMessage(service.findOneByISBN(isbn));
	}

	@ApiOperation(value = "通过code获取图书", notes = "通过code获取图书")
	@RequestMapping(value = "/findOneByCode/{code}", method = RequestMethod.GET)
	public JsonContainer<Book> findOneByCode(@PathVariable String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<BookMinInfo>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "通过标签，是否共享和用户编号(!=)分页查询", notes = "通过标签，是否共享和用户编号(!=)分页查询")
	@RequestMapping(value = "/findAllByTagCodeAndUserCodeNot", method = RequestMethod.POST)
	public JsonContainer<Page<BookMinInfo>> findAllByTagCodeAndUserCodeNot(@RequestBody @NotNull List<String> tagCodes, @RequestParam(required = false) Integer sharable, @RequestParam String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByTagCodeAndUserCodeNot(tagCodes, sharable, userCode, pageable));
	}

	@ApiOperation(value = "通过标签和是否共享分页查询", notes = "通过标签和是否共享分页查询")
	@RequestMapping(value = "/findAllByTagCode", method = RequestMethod.POST)
	public JsonContainer<Page<BookMinInfo>> findAllByTagCode(@RequestBody @NotNull List<String> tagCodes, @RequestParam(required = false) Integer sharable, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByTagCode(tagCodes, sharable, pageable));
	}

	@ApiOperation(value = "通过搜索框和用户编号(!=)分页查询", notes = "通过搜索框和用户编号(!=)分页查询")
	@RequestMapping(value = "/findAllBySearchAndUserCodeNot", method = RequestMethod.POST)
	public JsonContainer<Page<BookMinInfo>> findAllBySearchAndUserCodeNot(@RequestParam @NotNull String search, @RequestParam String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllBySearchAndUserCodeNot(search, userCode, pageable));
	}

	@ApiOperation(value = "通过搜索框和用户编号分页查询", notes = "通过搜索框和用户编号分页查询")
	@RequestMapping(value = "/findAllBySearch", method = RequestMethod.POST)
	public JsonContainer<Page<BookMinInfo>> findAllBySearch(@RequestParam @NotNull String search, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllBySearch(search, pageable));
	}

	@ApiOperation(value = "通过地址，是否共享和用户编号(!=)查询", notes = "通过地址，是否共享和用户编号(!=)查询")
	@RequestMapping(value = "/findAllByLbsIdAndUserCodeNot", method = RequestMethod.POST)
	public JsonContainer<Page<BookMinInfo>> findAllByLbsIdAndUserCodeNot(@RequestBody @NotNull List<String> lbsId, @RequestParam(required = false) Integer sharable, @RequestParam String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByLbsIdAndUserCodeNot(lbsId, sharable, userCode, pageable));
	}
}
