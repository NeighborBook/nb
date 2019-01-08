package com.nb.module.nb.customer.api.orderform.controller;

import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.domain.*;
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
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/orderform")
public class OrderFormController extends BaseController {

	@Autowired
	private IOrderFormService service;

	@ApiOperation(value = "书，借进和借出数量，及明细", notes = "书，借进和借出数量，及明细")
	@RequestMapping(value = "/count/{userCode}", method = RequestMethod.GET)
	public JsonContainer<UserBookAndOrderCount> count(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.count(userCode));
	}

	@ApiOperation(value = "未完成owner列表", notes = "未完成owner列表")
	@RequestMapping(value = "/borrow/owner/unfinished/{ownerUserCode}", method = RequestMethod.GET)
	public JsonContainer<List<OrderForm<OrderBorrow>>> findAllUnfinishedOrderFormsByOwnerUserCode(@PathVariable @NotBlank String ownerUserCode) {
		return setSuccessMessage(service.findAllUnfinishedOrderFormsByOwnerUserCode(ownerUserCode));
	}

	@ApiOperation(value = "未完成borrower列表", notes = "未完成borrower列表")
	@RequestMapping(value = "/borrow/borrower/unfinished/{borrowerUserCode}", method = RequestMethod.GET)
	public JsonContainer<List<OrderForm<OrderBorrow>>> findAllUnfinishedOrderFormsByBorrowerUserCode(@PathVariable @NotBlank String borrowerUserCode) {
		return setSuccessMessage(service.findAllUnfinishedOrderFormsByBorrowerUserCode(borrowerUserCode));
	}

	@ApiOperation(value = "owner列表", notes = "owner列表")
	@RequestMapping(value = "/borrow/owner/{ownerUserCode}", method = RequestMethod.GET)
	public JsonContainer<Page<OrderForm<OrderBorrow>>> findAllByOwnerUserCode(@PathVariable @NotBlank String ownerUserCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByOwnerUserCode(ownerUserCode, pageable));
	}

	@ApiOperation(value = "borrower列表", notes = "borrower列表")
	@RequestMapping(value = "/borrow/borrower/{borrowerUserCode}", method = RequestMethod.GET)
	public JsonContainer<Page<OrderForm<OrderBorrow>>> findAllByBorrowerUserCode(@PathVariable @NotBlank String borrowerUserCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByBorrowerUserCode(borrowerUserCode, pageable));
	}

	@ApiOperation(value = "借书详情", notes = "借书详情")
	@RequestMapping(value = "/borrow/{orderCode}", method = RequestMethod.GET)
	public JsonContainer<OrderForm<OrderBorrow>> findOrderBorrowByOrderCode(@PathVariable @NotBlank String orderCode) {
		return setSuccessMessage(service.findOrderBorrowByOrderCode(orderCode));
	}

	@ApiOperation(value = "借书", notes = "借书")
	@RequestMapping(value = "/borrow", method = RequestMethod.POST)
	public JsonContainer<OrderForm<OrderBorrow>> borrow(@RequestBody @Validated BorrowApply borrowApply) {
		return setSuccessMessage(service.borrow(borrowApply));
	}

	@ApiOperation(value = "借书流程", notes = "借书流程")
	@RequestMapping(value = "/borrow/flow", method = RequestMethod.POST)
	public JsonContainer<OrderForm<OrderBorrow>> borrowFlow(@RequestBody @Validated OrderFlow orderFlow) {
		return setSuccessMessage(service.borrowFlow(orderFlow));
	}

}
