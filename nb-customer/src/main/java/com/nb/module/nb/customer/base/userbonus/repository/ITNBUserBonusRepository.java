package com.nb.module.nb.customer.base.userbonus.repository;

import com.nb.module.nb.customer.base.userbonus.domain.TNBUserBonus;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userbonus")
public interface ITNBUserBonusRepository extends IDataRepository<TNBUserBonus, Integer> {

	TNBUserBonus findOneByUserCode(String userCode);

}
