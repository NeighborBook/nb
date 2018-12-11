package com.nb.module.nb.customer.base.partnerauthorization.repository;

import com.nb.module.nb.customer.base.partnerauthorization.domain.TNBPartnerAuthorization;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "partnerauthorization")
public interface ITNBPartnerAuthorizationRepository extends IDataRepository<TNBPartnerAuthorization, Integer> {

	TNBPartnerAuthorization findOneByCode(String code);

}
