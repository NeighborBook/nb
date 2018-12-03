package com.nb.module.nb.customer.base.userbonusdetail.repository;

import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userbonusdetail")
public interface ITNBUserBonusDetailRepository extends IDataRepository<TNBUserBonusDetail, Integer> {

	Page<TNBUserBonusDetail> findOneByUserCode(String userCode, Pageable pageable);

}
