package com.nb.module.nb.customer.base.uservolunteer.biz;

import com.nb.module.nb.customer.base.uservolunteer.domain.TNBUserVolunteer;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBUserVolunteerService extends IDataService<TNBUserVolunteer, Integer> {


	TNBUserVolunteer findOneByCode(String code);

}
