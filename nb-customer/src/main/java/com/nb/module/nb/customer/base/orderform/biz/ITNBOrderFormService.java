package com.nb.module.nb.customer.base.orderform.biz;


import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBOrderFormService extends IDataService<TNBOrderForm, Integer> {

	TNBOrderForm newInstance();

	TNBOrderForm findOneByCode(String code);
}
