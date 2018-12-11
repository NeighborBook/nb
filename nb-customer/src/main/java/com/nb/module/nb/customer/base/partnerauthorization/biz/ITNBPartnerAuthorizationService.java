package com.nb.module.nb.customer.base.partnerauthorization.biz;

import com.nb.module.nb.customer.base.partnerauthorization.domain.TNBPartnerAuthorization;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBPartnerAuthorizationService extends IDataService<TNBPartnerAuthorization, Integer> {

	TNBPartnerAuthorization newInstance();

	TNBPartnerAuthorization findOneByCode(String code);

}
