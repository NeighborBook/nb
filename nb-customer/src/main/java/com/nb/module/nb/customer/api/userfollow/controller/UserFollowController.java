package com.nb.module.nb.customer.api.userfollow.controller;

import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
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
@RequestMapping("/api/userfollow")
public class UserFollowController extends BaseController {

	@Autowired
	private IUserFollowService service;

	@ApiOperation(value = "关注列表", notes = "关注列表")
	@RequestMapping(value = "/userCode/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<UserFollow>> findAllByUserCode(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByUserCode(userCode, pageable));
	}

	@ApiOperation(value = "粉丝列表", notes = "粉丝列表")
	@RequestMapping(value = "/followUserCode/{followUserCode}", method = RequestMethod.GET)
	public JsonContainer<Page<UserFollow>> findAllByFollowUserCode(@PathVariable @NotBlank String followUserCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByFollowUserCode(followUserCode, pageable));
	}

	@ApiOperation(value = "关注", notes = "关注")
	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public JsonContainer follow(@RequestBody @Validated UserFollow userFollow) {
		service.save(userFollow);
		return setSuccessMessage();
	}

	@ApiOperation(value = "取消关注", notes = "取消关注")
	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public JsonContainer unfollow(@RequestBody @Validated UserFollow userFollow) {
		service.delete(userFollow);
		return setSuccessMessage();
	}

}
