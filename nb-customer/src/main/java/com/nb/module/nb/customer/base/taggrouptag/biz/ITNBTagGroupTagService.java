package com.nb.module.nb.customer.base.taggrouptag.biz;

import com.nb.module.nb.customer.base.taggrouptag.domain.TNBTagGroupTag;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBTagGroupTagService extends IDataService<TNBTagGroupTag, Integer> {

	TNBTagGroupTag findOneByTagGroupCodeAndTagCode(String tagGroupCode, String tagCode);

	List<TNBTagGroupTag> findAllByTagGroupCodeOrderByOrder(String tagGroupCode);

}
