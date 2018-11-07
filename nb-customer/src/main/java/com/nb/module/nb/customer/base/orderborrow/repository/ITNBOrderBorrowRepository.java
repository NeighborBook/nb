package com.nb.module.nb.customer.base.orderborrow.repository;

import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "orderborrow")
public interface ITNBOrderBorrowRepository extends IDataRepository<TNBOrderBorrow, Integer> {

	TNBOrderBorrow findOneByCode(String code);

}
