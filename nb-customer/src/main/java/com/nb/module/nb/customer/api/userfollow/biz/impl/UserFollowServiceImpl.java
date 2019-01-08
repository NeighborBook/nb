package com.nb.module.nb.customer.api.userfollow.biz.impl;

import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.constant.UserBookConstant;
import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.userfollow.domain.Fan;
import com.nb.module.nb.customer.api.userfollow.domain.Follower;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollowCount;
import com.nb.module.nb.customer.api.userfollow.exception.UserFollowCode;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.base.userfollow.biz.ITNBUserFollowService;
import com.nb.module.nb.customer.base.userfollow.domain.TNBUserFollow;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFollowServiceImpl extends CommonServiceImpl implements IUserFollowService {

	@Autowired
	private ITNBUserFollowService userFollowService;

	@Autowired
	private IUserBookService userBookService;
	@Autowired
	private IWeixinUserService weixinUserService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserFollowCount count(String userCode) {
		return new UserFollowCount(userFollowService.countByUserCode(userCode), userFollowService.countByFollowUserCode(userCode));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Follower> findAllFollowers(String userCode, Pageable pageable) {
		return userFollowService.findAllByUserCodeOrderByUpdatedDesc(userCode, pageable).map(e ->
				new Follower(convert(e), new NearbyUser(
						weixinUserService.findOneByCode(e.getFollowUserCode()),
						userBookService.findAllByTagCodeAndUserCode(null, UserBookConstant.SHARABLE, e.getFollowUserCode(), PageRequest.of(0, 3)),
						true
				))
		);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Fan> findAllFans(String userCode, Pageable pageable) {
		return userFollowService.findAllByFollowUserCodeOrderByUpdatedDesc(userCode, pageable).map(e ->
				new Fan(convert(e), new NearbyUser(
						weixinUserService.findOneByCode(e.getUserCode()),
						userBookService.findAllByTagCodeAndUserCode(null, UserBookConstant.SHARABLE, e.getUserCode(), PageRequest.of(0, 3)),
						true
				))
		);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Boolean isFollow(String userCode, String followUserCode) {
		TNBUserFollow po = userFollowService.findOneByUserCodeAndFollowUserCode(userCode, followUserCode);
		if (null != po) {
			return true;
		}
		return false;
	}

	private UserFollow convert(TNBUserFollow e) {
		return new UserFollow(e.getUserCode(), e.getFollowUserCode(), e.getSource());
	}

	@Override
	@Transactional
	public void save(UserFollow userFollow) {
		TNBUserFollow po = userFollowService.findOneByUserCodeAndFollowUserCode(userFollow.getUserCode(), userFollow.getFollowUserCode());
		// 您已经关注该用户
		if (null != po) {
			throw new BusinessException(UserFollowCode.UF0001, new Object[]{userFollow.getUserCode(), userFollow.getFollowUserCode()});
		}
		po = new TNBUserFollow();
		po.setUserCode(userFollow.getUserCode());
		po.setFollowUserCode(userFollow.getFollowUserCode());
		po.setSource(userFollow.getSource());
		userFollowService.save(po);
	}

	@Override
	@Transactional
	public void delete(UserFollow userFollow) {
		TNBUserFollow po = userFollowService.findOneByUserCodeAndFollowUserCode(userFollow.getUserCode(), userFollow.getFollowUserCode());
		// 您尚未关注该用户
		if (null == po) {
			throw new BusinessException(UserFollowCode.UF0002, new Object[]{userFollow.getUserCode(), userFollow.getFollowUserCode()});
		}
		userFollowService.delete(po);
	}
}
