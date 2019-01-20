package com.nb.module.nb.customer.base.event.biz;

import com.nb.module.nb.customer.base.event.domain.TNBEvent;
import com.zjk.module.common.data.biz.IDataService;

public interface ITNBEventService extends IDataService<TNBEvent, Integer> {

	TNBEvent newInstance();

	TNBEvent findOneByCode(String code);

}
