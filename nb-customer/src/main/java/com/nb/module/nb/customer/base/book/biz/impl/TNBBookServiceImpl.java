package com.nb.module.nb.customer.base.book.biz.impl;

import com.nb.module.nb.customer.base.book.biz.ITNBBookService;
import com.nb.module.nb.customer.base.book.domain.TNBBook;
import com.nb.module.nb.customer.base.book.repository.ITNBBookRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNBBookServiceImpl extends DataServiceImpl<TNBBook, Integer> implements ITNBBookService {

	@Autowired
	private ITNBBookRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TNBBook newInstance() {
		TNBBook po = new TNBBook();
		po.setCode(checkJsonContainer(codeClient.next(CustomerSerialCode.NBBOOK.getSerialGroup())));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBBook findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TNBBook findOneByISBN(String isbn) {
		return repository.findOneByISBN(isbn);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllByTagCode(List<String> tagCodes, Integer size, Pageable pageable) {
		if (null == tagCodes || tagCodes.isEmpty()) {
			return findAll(pageable);
		}
		return repository.findAllByTagCode(tagCodes, size, pageable);
	}
}
