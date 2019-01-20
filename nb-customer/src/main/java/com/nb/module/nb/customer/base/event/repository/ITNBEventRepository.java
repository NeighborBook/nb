package com.nb.module.nb.customer.base.event.repository;

import com.nb.module.nb.customer.base.event.domain.TNBEvent;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "event")
public interface ITNBEventRepository extends IDataRepository<TNBEvent, Integer> {

	TNBEvent findOneByCode(String code);

}
