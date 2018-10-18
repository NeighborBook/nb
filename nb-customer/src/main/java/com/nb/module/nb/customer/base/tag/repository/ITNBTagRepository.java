package com.nb.module.nb.customer.base.tag.repository;

import com.nb.module.nb.customer.base.tag.domain.TNBTag;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tag")
public interface ITNBTagRepository extends IDataRepository<TNBTag, Integer> {

	TNBTag findOneByCode(String code);

	TNBTag findOneByName(String name);

}
