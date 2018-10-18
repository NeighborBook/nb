package com.nb.module.nb.customer.base.tag.biz;


import com.nb.module.nb.customer.base.tag.domain.TNBTag;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBTagService extends IDataService<TNBTag, Integer> {

	TNBTag newInstance();

	TNBTag findOneByCode(String code);

	TNBTag findOneByName(String name);
}
