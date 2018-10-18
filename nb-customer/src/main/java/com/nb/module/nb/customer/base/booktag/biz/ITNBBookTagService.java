package com.nb.module.nb.customer.base.booktag.biz;

import com.nb.module.nb.customer.base.booktag.domain.TNBBookTag;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBBookTagService extends IDataService<TNBBookTag, Integer> {

	TNBBookTag findOneByBookCodeAndTagCode(String bookCode, String tagCode);

	List<TNBBookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode);

	List<TNBBookTag> findAllByTagCode(String tagCode);

}
