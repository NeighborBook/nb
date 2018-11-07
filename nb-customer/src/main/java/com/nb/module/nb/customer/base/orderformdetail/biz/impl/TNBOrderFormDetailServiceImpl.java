package com.nb.module.nb.customer.base.orderformdetail.biz.impl;

import com.nb.module.nb.customer.base.orderformdetail.biz.ITNBOrderFormDetailService;
import com.nb.module.nb.customer.base.orderformdetail.domain.TNBOrderFormDetail;
import com.nb.module.nb.customer.base.orderformdetail.repository.ITNBOrderFormDetailRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBOrderFormDetailServiceImpl extends DataServiceImpl<TNBOrderFormDetail, Integer> implements ITNBOrderFormDetailService {

	@Autowired
	private ITNBOrderFormDetailRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBOrderFormDetail> findAllByCode(String code) {
		return repository.findAllByCode(code);
	}

}
