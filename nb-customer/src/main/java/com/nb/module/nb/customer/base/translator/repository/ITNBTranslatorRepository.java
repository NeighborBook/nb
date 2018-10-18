package com.nb.module.nb.customer.base.translator.repository;

import com.nb.module.nb.customer.base.translator.domain.TNBTranslator;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "translator")
public interface ITNBTranslatorRepository extends IDataRepository<TNBTranslator, Integer> {

	List<TNBTranslator> findAllByCode(String code);

}
