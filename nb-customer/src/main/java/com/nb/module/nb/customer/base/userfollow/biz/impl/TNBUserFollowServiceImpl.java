package com.nb.module.nb.customer.base.userfollow.biz.impl;

import com.nb.module.nb.customer.base.userfollow.biz.ITNBUserFollowService;
import com.nb.module.nb.customer.base.userfollow.domain.TNBUserFollow;
import com.nb.module.nb.customer.base.userfollow.repository.ITNBUserFollowRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserFollowServiceImpl extends DataServiceImpl<TNBUserFollow, Integer> implements ITNBUserFollowService {

	@Autowired
	private ITNBUserFollowRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserFollow> findAllByUserCodeOrderByUpdatedDesc(String userCode, Pageable pageable) {
		return repository.findAllByUserCodeOrderByUpdatedDesc(userCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserFollow> findAllByFollowUserCodeOrderByUpdatedDesc(String followUserCode, Pageable pageable) {
		return repository.findAllByFollowUserCodeOrderByUpdatedDesc(followUserCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserFollow findOneByUserCodeAndFollowUserCode(String userCode, String followUserCode) {
		return repository.findOneByUserCodeAndFollowUserCode(userCode, followUserCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByUserCode(String userCode) {
		return repository.countByUserCode(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByFollowUserCode(String followUserCode) {
		return repository.countByFollowUserCode(followUserCode);
	}
}
