package com.nb.module.nb.customer.base.userbonusdetail.biz;

import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITNBUserBonusDetailService extends IDataService<TNBUserBonusDetail, Integer> {

	TNBUserBonusDetail newInstance();

	TNBUserBonusDetail findOneByCode(String code);

	Page<TNBUserBonusDetail> findAllByUserCode(String userCode, Pageable pageable);

}
