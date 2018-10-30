package com.nb.module.nb.customer.base.taggroup.repository;

import com.nb.module.nb.customer.base.taggroup.domain.TNBTagGroup;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "taggroup")
public interface ITNBTagGroupRepository extends IDataRepository<TNBTagGroup, Integer> {

	TNBTagGroup findOneByCode(String code);

	TNBTagGroup findOneByName(String name);

	List<TNBTagGroup> findAllByVisibleOrderByOrder(Integer visible);

}
