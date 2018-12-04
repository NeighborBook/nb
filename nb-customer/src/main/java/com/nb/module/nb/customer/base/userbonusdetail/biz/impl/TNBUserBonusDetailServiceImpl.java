package com.nb.module.nb.customer.base.userbonusdetail.biz.impl;

import com.nb.module.nb.customer.base.userbonusdetail.biz.ITNBUserBonusDetailService;
import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.nb.module.nb.customer.base.userbonusdetail.repository.ITNBUserBonusDetailRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBUserBonusDetailServiceImpl extends DataServiceImpl<TNBUserBonusDetail, Integer> implements ITNBUserBonusDetailService {

	@Autowired
	private ITNBUserBonusDetailRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBUserBonusDetail newInstance() {
		TNBUserBonusDetail po = new TNBUserBonusDetail();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBUSERBONUS.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserBonusDetail findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserBonusDetail> findAllByUserCode(String userCode, Pageable pageable) {
		return repository.findAllByUserCode(userCode, pageable);
	}
}
