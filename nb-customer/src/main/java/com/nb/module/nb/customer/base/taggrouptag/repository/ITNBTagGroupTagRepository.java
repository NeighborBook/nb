package com.nb.module.nb.customer.base.taggrouptag.repository;

import com.nb.module.nb.customer.base.taggrouptag.domain.TNBTagGroupTag;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "taggrouptag")
public interface ITNBTagGroupTagRepository extends IDataRepository<TNBTagGroupTag, Integer> {

	TNBTagGroupTag findOneByTagGroupCodeAndTagCode(String tagGroupCode, String tagCode);

	List<TNBTagGroupTag> findAllByTagGroupCodeOrderByPosition(String tagGroupCode);

	void deleteByTagGroupCode(String tagGroupCode);
}
