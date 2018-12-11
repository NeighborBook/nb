package com.nb.module.nb.customer.base.userintro.repository;

import com.nb.module.nb.customer.base.userintro.domain.TNBUserIntro;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userintro")
public interface ITNBUserIntroRepository extends IDataRepository<TNBUserIntro, Integer> {

	TNBUserIntro findOneByUserCode(String userCode);

	Page<TNBUserIntro> findAllByIntroUserCode(String introUserCode, Pageable pageable);

}
