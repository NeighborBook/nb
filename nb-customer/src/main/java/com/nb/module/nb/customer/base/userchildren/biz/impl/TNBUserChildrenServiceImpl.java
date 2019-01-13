package com.nb.module.nb.customer.base.userchildren.biz.impl;

import com.nb.module.nb.customer.base.userchildren.biz.ITNBUserChildrenService;
import com.nb.module.nb.customer.base.userchildren.domain.TNBUserChildren;
import com.nb.module.nb.customer.base.userchildren.repository.ITNBUserChildrenRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TNBUserChildrenServiceImpl extends DataServiceImpl<TNBUserChildren, Integer> implements ITNBUserChildrenService {

	@Autowired
	private ITNBUserChildrenRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBUserChildren newInstance() {
		TNBUserChildren po = new TNBUserChildren();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBCHILDREN.getSerialGroup())));
		return po;
	}

	@Override
	public List<TNBUserChildren> findAllByUserCode(String userCode) {
		return repository.findAllByUserCode(userCode);
	}

	@Override
	public TNBUserChildren findOneByCode(String code) {
		return repository.findOneByCode(code);
	}
}
