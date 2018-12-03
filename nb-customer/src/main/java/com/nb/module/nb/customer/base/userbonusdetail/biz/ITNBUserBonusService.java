package com.nb.module.nb.customer.base.userbonusdetail.biz;

import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITNBUserBonusService extends IDataService<TNBUserBonusDetail, Integer> {

	Page<TNBUserBonusDetail> findOneByUserCode(String userCode, Pageable pageable);

}
