package com.nb.module.nb.customer.api.userbonus.controller;

import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.domain.Adjust;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusDetail;
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

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/userbonus")
public class UserBonusController extends BaseController {

	@Autowired
	private IUserBonusService service;

	@ApiOperation(value = "用户积分", notes = "用户积分")
	@RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
	public JsonContainer<UserBonus> findOneOrInitByUserCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findOneOrInitByUserCode(userCode));
	}

	@ApiOperation(value = "用户积分明细", notes = "用户积分明细")
	@RequestMapping(value = "/detail/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<UserBonusDetail>> findOneOrInitByUserCode(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllUserBonusDetailByUserCode(userCode, pageable));
	}

	@ApiOperation(value = "调整积分", notes = "调整积分")
	@RequestMapping(value = "/adjust", method = RequestMethod.POST)
	public JsonContainer<UserBonus> adjust(@RequestBody @Validated Adjust adjust) {
		return setSuccessMessage(service.adjust(adjust));
	}
}
