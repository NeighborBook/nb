package com.nb.module.nb.customer.api.orderform.controller;

import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.domain.BorrowApply;
import com.nb.module.nb.customer.api.orderform.domain.OrderBorrow;
import com.nb.module.nb.customer.api.orderform.domain.OrderForm;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/orderform")
public class OrderFormController extends BaseController {

	@Autowired
	private IOrderFormService service;

	@ApiOperation(value = "借书", notes = "借书")
	@RequestMapping(value = "/borrow", method = RequestMethod.POST)
	public JsonContainer<OrderForm<OrderBorrow>> borrow(@RequestBody @Validated BorrowApply borrowApply) {
		return setSuccessMessage(service.borrow(borrowApply));
	}
}
