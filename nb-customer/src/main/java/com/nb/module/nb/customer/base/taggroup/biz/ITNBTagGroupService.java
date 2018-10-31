package com.nb.module.nb.customer.base.taggroup.biz;


import com.nb.module.nb.customer.base.taggroup.domain.TNBTagGroup;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBTagGroupService extends IDataService<TNBTagGroup, Integer> {

	TNBTagGroup newInstance();

	TNBTagGroup findOneByCode(String code);

	TNBTagGroup findOneByName(String name);

	List<TNBTagGroup> findAllByVisibleOrderByPosition(Integer visible);

	void deleteByCode(String code);
}
