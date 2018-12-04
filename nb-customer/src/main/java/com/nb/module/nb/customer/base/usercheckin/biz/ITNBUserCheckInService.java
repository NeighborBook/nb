package com.nb.module.nb.customer.base.usercheckin.biz;

import com.nb.module.nb.customer.base.usercheckin.domain.TNBUserCheckIn;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ITNBUserCheckInService extends IDataService<TNBUserCheckIn, Integer> {

	Page<TNBUserCheckIn> findAllByUserCode(String userCode, Pageable pageable);

	TNBUserCheckIn findOneByUserCodeAndCheckIn(String userCode, Date checkIn);

}
