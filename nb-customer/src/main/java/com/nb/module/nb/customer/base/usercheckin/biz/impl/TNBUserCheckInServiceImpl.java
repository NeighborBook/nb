package com.nb.module.nb.customer.base.usercheckin.biz.impl;

import com.nb.module.nb.customer.base.usercheckin.biz.ITNBUserCheckInService;
import com.nb.module.nb.customer.base.usercheckin.domain.TNBUserCheckIn;
import com.nb.module.nb.customer.base.usercheckin.repository.ITNBUserCheckInRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TNBUserCheckInServiceImpl extends DataServiceImpl<TNBUserCheckIn, Integer> implements ITNBUserCheckInService {

	@Autowired
	private ITNBUserCheckInRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBUserCheckIn newInstance() {
		TNBUserCheckIn po = new TNBUserCheckIn();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBUSERCHECKIN.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserCheckIn findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserCheckIn> findAllByUserCode(String userCode, Pageable pageable) {
		return repository.findAllByUserCode(userCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserCheckIn findOneByUserCodeAndCheckIn(String userCode, Date checkIn) {
		return repository.findOneByUserCodeAndCheckIn(userCode, checkIn);
	}
}
