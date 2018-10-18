package com.nb.module.nb.customer.api.isbn.controller;

import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.isbn.biz.IISBNService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/isbn")
public class ISBNController extends BaseController {

	@Autowired
	private IISBNService service;

	@ApiOperation(value = "通过isbn获取图书", notes = "通过isbn获取图书")
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public JsonContainer<Book> findOneByISBN(@PathVariable String isbn) {
		return setSuccessMessage(service.findOneByISBN(isbn));
	}

}
