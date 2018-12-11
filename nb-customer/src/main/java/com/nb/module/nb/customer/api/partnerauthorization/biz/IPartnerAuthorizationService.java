package com.nb.module.nb.customer.api.partnerauthorization.biz;

import com.nb.module.nb.customer.api.partnerauthorization.domain.PartnerAuthorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPartnerAuthorizationService {

	Page<PartnerAuthorization> findAll(Pageable pageable);

	void save(PartnerAuthorization partnerAuthorization);

}
