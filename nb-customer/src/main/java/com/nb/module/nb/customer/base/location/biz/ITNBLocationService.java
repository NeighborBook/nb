package com.nb.module.nb.customer.base.location.biz;

import com.nb.module.nb.customer.base.location.domain.TNBLocation;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBLocationService extends IDataService<TNBLocation, Integer> {

	TNBLocation findOneByLbsId(String lbsId);

}
