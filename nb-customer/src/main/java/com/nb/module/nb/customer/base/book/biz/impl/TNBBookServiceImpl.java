package com.nb.module.nb.customer.base.book.biz.impl;

import com.nb.module.nb.customer.base.book.biz.ITNBBookService;
import com.nb.module.nb.customer.base.book.domain.TNBBook;
import com.nb.module.nb.customer.base.book.repository.ITNBBookRepository;
import com.nb.module.nb.customer.serialcode.CustomerSerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import com.zjk.module.common.data.specification.SpecificationOperate;
import com.zjk.module.common.data.util.SpecificationUtil;
import org.apache.commons.lang3.StringUtils;
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
	public Page<TNBBook> findAllByTagCode(List<String> tagCodes, Integer size, Integer sharable, Pageable pageable) {
		Page<TNBBook> result;
		if (null == tagCodes || tagCodes.isEmpty()) {
			if (null == sharable) {
				result = findAll(pageable);
			} else {
				result = repository.findAllBySharable(sharable, pageable);
			}
		} else {
			if (null == sharable) {
				result = repository.findAllByTagCode(tagCodes, size, pageable);
			} else {
				result = repository.findAllByTagCodeAndSharable(tagCodes, size, sharable, pageable);
			}
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllByTagCodeAndUserCodeNot(List<String> tagCodes, Integer size, Integer sharable, String userCode, Pageable pageable) {
		Page<TNBBook> result;
		if (null == tagCodes || tagCodes.isEmpty()) {
			if (null == sharable) {
				result = repository.findAllByUserCodeNot(userCode, pageable);
			} else {
				result = repository.findAllBySharableAndUserCodeNot(sharable, userCode, pageable);
			}
		} else {
			if (null == sharable) {
				result = repository.findAllByTagCodeAndUserCodeNot(tagCodes, size, userCode, pageable);
			} else {
				result = repository.findAllByTagCodeAndSharableAndUserCodeNot(tagCodes, size, sharable, userCode, pageable);
			}
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllByTagCodeAndUserCode(List<String> tagCodes, Integer size, Integer sharable, String userCode, Pageable pageable) {
		Page<TNBBook> result;
		if (null == tagCodes || tagCodes.isEmpty()) {
			if (null == sharable) {
				result = repository.findAllByUserCode(userCode, pageable);
			} else {
				result = repository.findAllBySharableAndUserCode(sharable, userCode, pageable);
			}
		} else {
			if (null == sharable) {
				result = repository.findAllByTagCodeAndUserCode(tagCodes, size, userCode, pageable);
			} else {
				result = repository.findAllByTagCodeAndSharableAndUserCode(tagCodes, size, sharable, userCode, pageable);
			}
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllBySearch(String search, Pageable pageable) {
		if (StringUtils.isBlank(search)) {
			return findAll(pageable);
		}
		return repository.findAllBySearch(SpecificationUtil.like(SpecificationOperate.LIKE, search), pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllBySearchAndUserCodeNot(String search, String userCode, Pageable pageable) {
		if (StringUtils.isBlank(search)) {
			return repository.findAllByUserCodeNot(userCode, pageable);
		}
		return repository.findAllBySearchAndUserCodeNot(SpecificationUtil.like(SpecificationOperate.LIKE, search), userCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllBySearchAndUserCode(String search, String userCode, Pageable pageable) {
		if (StringUtils.isBlank(search)) {
			return repository.findAllByUserCode(userCode, pageable);
		}
		return repository.findAllBySearchAndUserCode(SpecificationUtil.like(SpecificationOperate.LIKE, search), userCode, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TNBBook> findAllByLbsIdAndUserCodeNot(List<String> lbsId, Integer sharable, String userCode, Pageable pageable) {
		if (null == sharable) {
			return repository.findAllByLbsIdAndUserCodeNot(lbsId, userCode, pageable);
		}
		return repository.findAllByLbsIdAndSharableAndUserCodeNot(lbsId, sharable, userCode, pageable);
	}
}
