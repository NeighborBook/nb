package com.nb.module.nb.customer.base.userevent.biz.impl;

import com.nb.module.nb.customer.base.userevent.biz.ITNBUserEventService;
import com.nb.module.nb.customer.base.userevent.domain.TNBUserEvent;
import com.nb.module.nb.customer.base.userevent.repository.ITNBUserEventRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserEventServiceImpl extends DataServiceImpl<TNBUserEvent, Integer> implements ITNBUserEventService {

	@Autowired
	private ITNBUserEventRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserEvent> findAllByUserCodeAndStatus(String userCode, Integer status, Pageable pageable) {
		return repository.findAllByUserCodeAndStatus(userCode, status, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserEvent> findAllByEventCodeAndStatus(String eventCode, Integer status, Pageable pageable) {
		return repository.findAllByEventCodeAndStatus(eventCode, status, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByEventCodeAndStatus(String eventCode, Integer status) {
		return repository.countByEventCodeAndStatus(eventCode, status);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserEvent findOneByUserCodeAndEventCode(String userCode, String eventCode) {
		return repository.findOneByUserCodeAndEventCode(userCode, eventCode);
	}
}
