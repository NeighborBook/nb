package com.nb.module.nb.customer.api.weixin.user.controller;

import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.nb.module.nb.customer.api.weixin.user.domain.UserLocation;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/weixin/user")
public class WeixinUserController extends BaseController {

	@Autowired
	private IWeixinUserService service;

	@ApiOperation(value = "获取用户信息", notes = "获取用户信息")
	@RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
	public JsonContainer<User> findOneByCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findOneByCode(userCode));
	}

	@ApiOperation(value = "获取完整用户信息", notes = "获取完整用户信息")
	@RequestMapping(value = "/full/{userCode}", method = RequestMethod.GET)
	public JsonContainer<User> findFullOneByCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findFullOneByCode(userCode));
	}

	@ApiOperation(value = "更新手机号", notes = "更新手机号")
	@RequestMapping(value = "/updateMobile", method = RequestMethod.PUT)
	public JsonContainer<User> updateMobile(@RequestBody @Validated Mobile mobile) {
		return setSuccessMessage(service.updateMobile(mobile));
	}

	@ApiOperation(value = "更新姓名和职业", notes = "更新姓名和职业")
	@RequestMapping(value = "/updateNameAndProfession", method = RequestMethod.PUT)
	public JsonContainer updateNameAndProfession(@RequestParam String code, @RequestParam String name, @RequestParam String profession) {
		service.updateNameAndProfession(code, name, profession);
		return setSuccessMessage();
	}

	@ApiOperation(value = "保存用户地址信息", notes = "保存用户地址信息")
	@RequestMapping(value = "/saveUserLocation", method = RequestMethod.POST)
	public JsonContainer saveUserLocation(@RequestBody @Validated UserLocation userLocation) {
		service.saveUserLocation(userLocation);
		return setSuccessMessage();
	}

	@ApiOperation(value = "删除用户地址信息", notes = "删除用户地址信息")
	@RequestMapping(value = "/deleteUserLocation", method = RequestMethod.DELETE)
	public JsonContainer deleteUserLocation(@RequestBody @Validated UserLocation userLocation) {
		service.deleteUserLocation(userLocation);
		return setSuccessMessage();
	}

	@ApiOperation(value = "获取用户地址信息", notes = "获取用户地址信息")
	@RequestMapping(value = "/userLocation/{userCode}", method = RequestMethod.GET)
	public JsonContainer<List<UserLocation>> findUserLocationByCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findUserLocationByCode(userCode));
	}

}
