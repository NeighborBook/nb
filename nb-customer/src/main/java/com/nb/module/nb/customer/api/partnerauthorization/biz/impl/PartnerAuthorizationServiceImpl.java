package com.nb.module.nb.customer.api.partnerauthorization.biz.impl;

import com.nb.module.nb.customer.api.partnerauthorization.biz.IPartnerAuthorizationService;
import com.nb.module.nb.customer.api.partnerauthorization.domain.PartnerAuthorization;
import com.nb.module.nb.customer.base.partnerauthorization.biz.ITNBPartnerAuthorizationService;
import com.nb.module.nb.customer.base.partnerauthorization.domain.TNBPartnerAuthorization;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartnerAuthorizationServiceImpl extends CommonServiceImpl implements IPartnerAuthorizationService {

	@Autowired
	private ITNBPartnerAuthorizationService partnerAuthorizationService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<PartnerAuthorization> findAll(Pageable pageable) {
		return partnerAuthorizationService.findAll(pageable).map(e -> new PartnerAuthorization(e.getCode(), e.getUsername(), e.getPassword(), e.getUsage()));
	}

	@Override
	@Transactional
	public void save(PartnerAuthorization partnerAuthorization) {
		TNBPartnerAuthorization po = partnerAuthorizationService.findOneByCode(partnerAuthorization.getCode());
		if (null == po) {
			po = partnerAuthorizationService.newInstance();
		}
		po.setUsername(partnerAuthorization.getUsername());
		po.setPassword(partnerAuthorization.getPassword());
		po.setUsage(partnerAuthorization.getUsage());
		partnerAuthorizationService.save(po);
	}
}
