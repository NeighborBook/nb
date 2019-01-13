package com.nb.module.nb.customer.base.userchildren.biz;

import com.nb.module.nb.customer.base.userchildren.domain.TNBUserChildren;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBUserChildrenService extends IDataService<TNBUserChildren, Integer> {

	TNBUserChildren newInstance();

	List<TNBUserChildren> findAllByUserCode(String userCode);

	TNBUserChildren findOneByCode(String code);

}
