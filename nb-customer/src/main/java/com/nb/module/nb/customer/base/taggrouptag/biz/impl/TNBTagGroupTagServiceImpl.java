package com.nb.module.nb.customer.base.taggrouptag.biz.impl;

import com.nb.module.nb.customer.base.taggrouptag.biz.ITNBTagGroupTagService;
import com.nb.module.nb.customer.base.taggrouptag.domain.TNBTagGroupTag;
import com.nb.module.nb.customer.base.taggrouptag.repository.ITNBTagGroupTagRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBTagGroupTagServiceImpl extends DataServiceImpl<TNBTagGroupTag, Integer> implements ITNBTagGroupTagService {

	@Autowired
	private ITNBTagGroupTagRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBTagGroupTag findOneByTagGroupCodeAndTagCode(String tagGroupCode, String tagCode) {
		return repository.findOneByTagGroupCodeAndTagCode(tagGroupCode, tagCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBTagGroupTag> findAllByTagGroupCodeOrderByPosition(String tagGroupCode) {
		return repository.findAllByTagGroupCodeOrderByPosition(tagGroupCode);
	}
}
