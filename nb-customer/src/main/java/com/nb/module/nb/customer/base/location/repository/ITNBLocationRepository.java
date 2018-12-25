package com.nb.module.nb.customer.base.location.repository;

import com.nb.module.nb.customer.base.location.domain.TNBLocation;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "location")
public interface ITNBLocationRepository extends IDataRepository<TNBLocation, Integer> {

	TNBLocation findOneByLbsId(String lbsId);

}
