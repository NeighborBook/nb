package com.nb.module.nb.customer.base.orderformdetail.biz;


import com.nb.module.nb.customer.base.orderformdetail.domain.TNBOrderFormDetail;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBOrderFormDetailService extends IDataService<TNBOrderFormDetail, Integer> {

	List<TNBOrderFormDetail> findAllByCode(String code);

}
