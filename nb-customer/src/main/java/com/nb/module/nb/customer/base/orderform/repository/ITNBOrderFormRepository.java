package com.nb.module.nb.customer.base.orderform.repository;

import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "orderform")
public interface ITNBOrderFormRepository extends IDataRepository<TNBOrderForm, Integer> {

	TNBOrderForm findOneByCode(String code);

	List<TNBOrderForm> findAllByUserCodeAndOrderTypeAndOrderStatus(String userCode, Integer orderType, Integer orderStatus);

}
