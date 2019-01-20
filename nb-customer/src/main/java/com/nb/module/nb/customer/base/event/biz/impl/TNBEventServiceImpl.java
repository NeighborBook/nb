package com.nb.module.nb.customer.base.event.biz.impl;

import com.nb.module.nb.customer.base.event.biz.ITNBEventService;
import com.nb.module.nb.customer.base.event.domain.TNBEvent;
import com.nb.module.nb.customer.base.event.repository.ITNBEventRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TNBEventServiceImpl extends DataServiceImpl<TNBEvent, Integer> implements ITNBEventService {

	@Autowired
	private ITNBEventRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBEvent newInstance() {
		TNBEvent po = new TNBEvent();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBEVENT.getSerialGroup())));
		return po;
	}

	@Override
	public TNBEvent findOneByCode(String code) {
		return repository.findOneByCode(code);
	}
}
