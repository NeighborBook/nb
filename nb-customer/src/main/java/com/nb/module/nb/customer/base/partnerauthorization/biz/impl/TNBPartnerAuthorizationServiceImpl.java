package com.nb.module.nb.customer.base.partnerauthorization.biz.impl;

import com.nb.module.nb.customer.base.partnerauthorization.biz.ITNBPartnerAuthorizationService;
import com.nb.module.nb.customer.base.partnerauthorization.domain.TNBPartnerAuthorization;
import com.nb.module.nb.customer.base.partnerauthorization.repository.ITNBPartnerAuthorizationRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBPartnerAuthorizationServiceImpl extends DataServiceImpl<TNBPartnerAuthorization, Integer> implements ITNBPartnerAuthorizationService {

	@Autowired
	private ITNBPartnerAuthorizationRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBPartnerAuthorization newInstance() {
		TNBPartnerAuthorization po = new TNBPartnerAuthorization();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBPARTNERAUTH.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBPartnerAuthorization findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

}
