package com.nb.module.nb.customer.base.orderform.biz;


import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBOrderFormService extends IDataService<TNBOrderForm, Integer> {

	TNBOrderForm newInstance();

	TNBOrderForm findOneByCode(String code);

	List<TNBOrderForm> findAllByUserCodeAndOrderTypeAndOrderStatus(String userCode, Integer orderType, Integer orderStatus);
}
