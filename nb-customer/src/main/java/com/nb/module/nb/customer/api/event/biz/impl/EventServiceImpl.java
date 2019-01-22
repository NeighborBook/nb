package com.nb.module.nb.customer.api.event.biz.impl;

import com.nb.module.nb.customer.api.event.biz.IEventService;
import com.nb.module.nb.customer.api.event.domain.Event;
import com.nb.module.nb.customer.base.event.biz.ITNBEventService;
import com.nb.module.nb.customer.base.event.domain.TNBEvent;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventServiceImpl extends CommonServiceImpl implements IEventService {

	@Autowired
	private ITNBEventService eventService;

	@Override
	@Transactional
	public void save(Event event) {
		TNBEvent po = eventService.findOneByCode(event.getCode());
		if (null == po) {
			po = eventService.newInstance();
		}
		po.setName(event.getName());
		po.setBeginDate(event.getBeginDate());
		po.setEndDate(event.getEndDate());
		po.setLocation(event.getLocation());
		po.setEnterBeginDate(event.getEnterBeginDate());
		po.setEnterEndDate(event.getEnterEndDate());
		po.setQuota(event.getQuota());
		po.setMaxQuota(event.getMaxQuota());
		eventService.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Event> findAll(Pageable pageable) {
		return eventService.findAll(pageable).map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Event findOneByCode(String code) {
		return mapOneIfNotNull(eventService.findOneByCode(code), e -> convert(e));
	}

	private Event convert(TNBEvent e) {
		return new Event(e.getCode(), e.getName(), e.getBeginDate(), e.getEndDate(), e.getLocation(),
				e.getEnterBeginDate(), e.getEnterEndDate(), e.getQuota(), e.getMaxQuota());
	}
}
