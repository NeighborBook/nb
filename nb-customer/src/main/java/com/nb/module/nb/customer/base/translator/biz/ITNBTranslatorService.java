package com.nb.module.nb.customer.base.translator.biz;

import com.nb.module.nb.customer.base.translator.domain.TNBTranslator;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBTranslatorService extends IDataService<TNBTranslator, Integer> {

	List<TNBTranslator> findAllByCode(String code);

}
