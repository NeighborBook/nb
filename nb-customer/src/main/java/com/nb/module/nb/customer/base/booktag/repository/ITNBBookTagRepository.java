package com.nb.module.nb.customer.base.booktag.repository;

import com.nb.module.nb.customer.base.booktag.domain.TNBBookTag;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "booktag")
public interface ITNBBookTagRepository extends IDataRepository<TNBBookTag, Integer> {

	TNBBookTag findOneByBookCodeAndTagCode(String bookCode, String tagCode);

	List<TNBBookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode);

	List<TNBBookTag> findAllByTagCode(String tagCode);
}
