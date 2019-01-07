package com.nb.module.nb.customer.api.userfollow.controller;

import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.userfollow.domain.Fan;
import com.nb.module.nb.customer.api.userfollow.domain.Follower;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollowCount;
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

	@ApiOperation(value = "关注及粉丝数量", notes = "关注及粉丝数量")
	@RequestMapping(value = "/count/{userCode}", method = RequestMethod.GET)
	public JsonContainer<UserFollowCount> count(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.count(userCode));
	}

	@ApiOperation(value = "关注列表", notes = "关注列表")
	@RequestMapping(value = "/followers/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<Follower>> findAllFollowers(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllFollowers(userCode, pageable));
	}

	@ApiOperation(value = "粉丝列表", notes = "粉丝列表")
	@RequestMapping(value = "/fans/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<Fan>> findAllFans(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllFans(userCode, pageable));
	}

	@ApiOperation(value = "是否关注", notes = "是否关注")
	@RequestMapping(value = "/isFollow", method = RequestMethod.GET)
	public JsonContainer<Boolean> isFollow(@RequestParam @NotBlank String userCode, @RequestParam @NotBlank String followUserCode) {
		return setSuccessMessage(service.isFollow(userCode, followUserCode));
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
