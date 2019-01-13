package com.nb.module.nb.customer.base.userchildren.repository;

import com.nb.module.nb.customer.base.userchildren.domain.TNBUserChildren;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "userchildren")
public interface ITNBUserChildrenRepository extends IDataRepository<TNBUserChildren, Integer> {

	List<TNBUserChildren> findAllByUserCode(String userCode);

	TNBUserChildren findOneByCode(String code);

}
