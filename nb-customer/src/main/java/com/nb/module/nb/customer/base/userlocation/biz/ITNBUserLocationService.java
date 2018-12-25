package com.nb.module.nb.customer.base.userlocation.biz;

import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBUserLocationService extends IDataService<TNBUserLocation, Integer> {

	List<TNBUserLocation> findAllByUserCode(String userCode);

	TNBUserLocation findOneByUserCodeAndLbsIdAndTagCode(String userCode, String lbsId, String tagCode);

}
