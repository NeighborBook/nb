package com.nb.module.nb.customer.api.userevent.biz.impl;

import com.nb.module.nb.customer.api.event.biz.IEventService;
import com.nb.module.nb.customer.api.event.domain.Event;
import com.nb.module.nb.customer.api.userevent.biz.IUserEventService;
import com.nb.module.nb.customer.api.userevent.constant.UserEventConstant;
import com.nb.module.nb.customer.api.userevent.domain.UserEvent;
import com.nb.module.nb.customer.api.userevent.exception.UserEventCode;
import com.nb.module.nb.customer.base.userevent.biz.ITNBUserEventService;
import com.nb.module.nb.customer.base.userevent.domain.TNBUserEvent;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserEventServiceImpl extends CommonServiceImpl implements IUserEventService {

	@Autowired
	private ITNBUserEventService userEventService;
	@Autowired
	private IEventService eventService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserEvent> findAllByUserCodeAndStatus(String userCode, Integer status, Pageable pageable) {
		return userEventService.findAllByUserCodeAndStatus(userCode, status, pageable)
				.map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserEvent> findAllByEventCodeAndStatus(String eventCode, Integer status, Pageable pageable) {
		return userEventService.findAllByEventCodeAndStatus(eventCode, status, pageable)
				.map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByEventCodeAndStatus(String eventCode, Integer status) {
		return userEventService.countByEventCodeAndStatus(eventCode, status);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserEvent findOneByUserCodeAndEventCode(String userCode, String eventCode) {
		return mapOneIfNotNull(userEventService.findOneByUserCodeAndEventCode(userCode, eventCode)
				, e -> convert(e));
	}

	private UserEvent convert(TNBUserEvent e) {
		return new UserEvent(e.getUserCode(), e.getEventCode());
	}

	@Override
	@Transactional
	public void signUp(UserEvent userEvent) {
		TNBUserEvent po = userEventService.findOneByUserCodeAndEventCode(userEvent.getUserCode(), userEvent.getEventCode());
		if (po == null) {
			po = new TNBUserEvent();
		} else {
			// 报过名了
			if (UserEventConstant.STATUS_1.equals(po.getStatus())) {
				throw new BusinessException(UserEventCode.UE0002, new Object[]{userEvent.getUserCode(), userEvent.getEventCode()});
			}
		}
		// 查找活动
		Event event = eventService.findOneByCode(userEvent.getEventCode());
		// 判断活动剩余名额
		if (event.getSurplus() <= 0) {
			throw new BusinessException(UserEventCode.UE0001, new Object[]{userEvent.getUserCode(), userEvent.getEventCode()});
		}
		po.setUserCode(userEvent.getUserCode());
		po.setEventCode(userEvent.getEventCode());
		po.setStatus(UserEventConstant.STATUS_1);
		userEventService.save(po);
	}

	@Override
	@Transactional
	public void cancel(UserEvent userEvent) {
		TNBUserEvent po = userEventService.findOneByUserCodeAndEventCode(userEvent.getUserCode(), userEvent.getEventCode());
		if (po == null) {
			throw new BusinessException(UserEventCode.UE0003, new Object[]{userEvent.getUserCode(), userEvent.getEventCode()});
		}
		po.setStatus(UserEventConstant.STATUS_0);
		userEventService.save(po);
	}
}
