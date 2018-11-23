package com.nb.module.nb.customer.base.userlocation.repository;

import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userlocation")
public interface ITNBUserLocationRepository extends IDataRepository<TNBUserLocation, Integer> {

	TNBUserLocation findOneByUserCode(String userCode);

}
