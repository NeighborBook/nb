package com.nb.module.nb.customer.base.taggroup.biz.impl;

import com.nb.module.nb.customer.base.taggroup.biz.ITNBTagGroupService;
import com.nb.module.nb.customer.base.taggroup.domain.TNBTagGroup;
import com.nb.module.nb.customer.base.taggroup.repository.ITNBTagGroupRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBTagGroupServiceImpl extends DataServiceImpl<TNBTagGroup, Integer> implements ITNBTagGroupService {

	@Autowired
	private ITNBTagGroupRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBTagGroup newInstance() {
		TNBTagGroup po = new TNBTagGroup();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBTAGGROUP.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBTagGroup findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBTagGroup findOneByName(String name) {
		return repository.findOneByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBTagGroup> findAllByVisibleOrderByOrder(Integer visible) {
		return repository.findAllByVisibleOrderByOrder(visible);
	}
}
