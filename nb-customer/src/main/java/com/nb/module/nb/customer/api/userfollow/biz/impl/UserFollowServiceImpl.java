package com.nb.module.nb.customer.api.userfollow.biz.impl;

import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import com.nb.module.nb.customer.api.userfollow.exception.UserFollowCode;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.base.userfollow.biz.ITNBUserFollowService;
import com.nb.module.nb.customer.base.userfollow.domain.TNBUserFollow;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFollowServiceImpl extends CommonServiceImpl implements IUserFollowService {

	@Autowired
	private ITNBUserFollowService userFollowService;

	@Autowired
	private IWeixinUserService weixinUserService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserFollow> findAllByUserCode(String userCode, Pageable pageable) {
		return userFollowService.findAllByUserCode(userCode, pageable).map(e -> {
			UserFollow userFollow = convert(e);
			userFollow.setFollowUser(weixinUserService.findOneByCode(userFollow.getFollowUserCode()));
			return userFollow;
		});
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserFollow> findAllByFollowUserCode(String followUserCode, Pageable pageable) {
		return userFollowService.findAllByFollowUserCode(followUserCode, pageable).map(e -> {
			UserFollow userFollow = convert(e);
			userFollow.setUser(weixinUserService.findOneByCode(userFollow.getUserCode()));
			return userFollow;
		});
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserFollow findOneByUserCodeAndFollowUserCode(String userCode, String followUserCode) {
		return mapOneIfNotNull(userFollowService.findOneByUserCodeAndFollowUserCode(userCode, followUserCode), e -> convert(e));
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
