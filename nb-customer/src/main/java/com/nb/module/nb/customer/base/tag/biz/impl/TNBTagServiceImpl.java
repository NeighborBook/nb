package com.nb.module.nb.customer.base.tag.biz.impl;

import com.nb.module.nb.customer.base.tag.biz.ITNBTagService;
import com.nb.module.nb.customer.base.tag.domain.TNBTag;
import com.nb.module.nb.customer.base.tag.repository.ITNBTagRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBTagServiceImpl extends DataServiceImpl<TNBTag, Integer> implements ITNBTagService {

	@Autowired
	private ITNBTagRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBTag newInstance() {
		TNBTag po = new TNBTag();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBTAG.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBTag findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBTag findOneByName(String name) {
		return repository.findOneByName(name);
	}
}
