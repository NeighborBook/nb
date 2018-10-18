package com.nb.module.nb.customer.base.translator.biz.impl;

import com.nb.module.nb.customer.base.translator.biz.ITNBTranslatorService;
import com.nb.module.nb.customer.base.translator.domain.TNBTranslator;
import com.nb.module.nb.customer.base.translator.repository.ITNBTranslatorRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBTranslatorServiceImpl extends DataServiceImpl<TNBTranslator, Integer> implements ITNBTranslatorService {

	@Autowired
	private ITNBTranslatorRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBTranslator> findAllByCode(String code) {
		return repository.findAllByCode(code);
	}

}
