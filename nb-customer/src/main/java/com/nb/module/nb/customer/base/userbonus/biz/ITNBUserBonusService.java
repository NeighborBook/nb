package com.nb.module.nb.customer.base.userbonus.biz;

import com.nb.module.nb.customer.base.userbonus.domain.TNBUserBonus;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBUserBonusService extends IDataService<TNBUserBonus, Integer> {

	TNBUserBonus findOneByUserCode(String userCode);

}
