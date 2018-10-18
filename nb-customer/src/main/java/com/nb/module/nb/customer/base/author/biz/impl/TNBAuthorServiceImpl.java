package com.nb.module.nb.customer.base.author.biz.impl;

import com.nb.module.nb.customer.base.author.biz.ITNBAuthorService;
import com.nb.module.nb.customer.base.author.domain.TNBAuthor;
import com.nb.module.nb.customer.base.author.repository.ITNBAuthorRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBAuthorServiceImpl extends DataServiceImpl<TNBAuthor, Integer> implements ITNBAuthorService {

	@Autowired
	private ITNBAuthorRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TNBAuthor> findAllByCode(String code) {
		return repository.findAllByCode(code);
	}

}
