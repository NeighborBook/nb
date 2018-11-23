package com.nb.module.nb.customer.base.userlocation.biz;

import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBUserLocationService extends IDataService<TNBUserLocation, Integer> {

	TNBUserLocation findOneByUserCode(String userCode);

}
