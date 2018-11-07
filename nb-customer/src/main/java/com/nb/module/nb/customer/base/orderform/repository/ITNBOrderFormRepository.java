package com.nb.module.nb.customer.base.orderform.repository;

import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "orderform")
public interface ITNBOrderFormRepository extends IDataRepository<TNBOrderForm, Integer> {

	TNBOrderForm findOneByCode(String code);

}
