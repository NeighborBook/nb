package com.nb.module.nb.customer.base.orderform.biz.impl;

import com.nb.module.nb.customer.base.orderform.biz.ITNBOrderFormService;
import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.nb.module.nb.customer.base.orderform.repository.ITNBOrderFormRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TNBOrderFormServiceImpl extends DataServiceImpl<TNBOrderForm, Integer> implements ITNBOrderFormService {

	@Autowired
	private ITNBOrderFormRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBOrderForm newInstance() {
		TNBOrderForm po = new TNBOrderForm();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBORDERFORM.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBOrderForm findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

}
