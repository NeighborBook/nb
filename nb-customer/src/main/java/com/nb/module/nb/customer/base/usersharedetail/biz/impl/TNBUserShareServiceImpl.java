package com.nb.module.nb.customer.base.usersharedetail.biz.impl;

import com.nb.module.nb.customer.base.usersharedetail.biz.ITNBUserShareService;
import com.nb.module.nb.customer.base.usersharedetail.domain.TNBUserShare;
import com.nb.module.nb.customer.base.usersharedetail.repository.ITNBUserShareRepository;
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
import java.util.List;

@Service
public class TNBUserShareServiceImpl extends DataServiceImpl<TNBUserShare, Integer> implements ITNBUserShareService {

	@Autowired
	private ITNBUserShareRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBUserShare newInstance() {
		TNBUserShare po = new TNBUserShare();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBUSERSHARE.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBUserShare findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBUserShare> findAllByUserCode(String userCode, Pageable pageable) {
		return repository.findAllByUserCode(userCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBUserShare> findAllByUserCodeAndShareDate(String userCode, Date shareDate) {
		return repository.findAllByUserCodeAndShareDate(userCode, shareDate);
	}

}
