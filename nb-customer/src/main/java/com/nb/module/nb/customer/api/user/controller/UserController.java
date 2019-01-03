package com.nb.module.nb.customer.api.user.controller;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollowCount;
import com.zjk.module.common.authorization.client.api.user.domain.User;
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
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService service;

	@ApiOperation(value = "根据用户名查询用户信息", notes = "根据用户名查询用户信息")
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public JsonContainer<User> findOneByUsername(@PathVariable @NotBlank String username, @RequestParam(required = false) String plugin) {
		return setSuccessMessage(service.findOneByUsername(username, plugin));
	}

}
