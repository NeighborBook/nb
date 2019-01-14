package com.nb.module.nb.customer.base.uservolunteer.repository;

import com.nb.module.nb.customer.base.uservolunteer.domain.TNBUserVolunteer;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "uservolunteer")
public interface ITNBUserVolunteerRepository extends IDataRepository<TNBUserVolunteer, Integer> {

	TNBUserVolunteer findOneByCode(String code);

}
