package com.nb.module.nb.customer.base.orderformdetail.repository;

import com.nb.module.nb.customer.base.orderformdetail.domain.TNBOrderFormDetail;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "orderformdetail")
public interface ITNBOrderFormDetailRepository extends IDataRepository<TNBOrderFormDetail, Integer> {

	List<TNBOrderFormDetail> findAllByCode(String code);

}
