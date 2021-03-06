package com.nb.module.nb.customer.api.nearby.biz.impl;

import com.nb.module.nb.customer.api.nearby.biz.INearbyService;
import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.constant.UserBookConstant;
import com.nb.module.nb.customer.api.userfollow.biz.IUserFollowService;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NearbyServiceImpl extends CommonServiceImpl implements INearbyService {

	@Autowired
	private IUserBookService userBookService;
	@Autowired
	private IUserFollowService userFollowService;
	@Autowired
	private IWeixinUserService weixinUserService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<NearbyUser> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable) {
		return userBookService.findAllByLbsIdInAndUserCodeNot(lbsId, userCode, pageable)
				.map(e -> new NearbyUser(weixinUserService.findOneByCode(e.getUserCode()),
						userBookService.findAllByTagCodeAndUserCode(null, UserBookConstant.SHARABLE, e.getUserCode(), PageRequest.of(0, 3)),
						userFollowService.isFollow(userCode, e.getUserCode())));
	}
}
