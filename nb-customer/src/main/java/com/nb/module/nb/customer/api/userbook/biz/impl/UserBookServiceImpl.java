package com.nb.module.nb.customer.api.userbook.biz.impl;

import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.nb.module.nb.customer.base.userbook.biz.ITNBUserBookService;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBookServiceImpl extends CommonServiceImpl implements IUserBookService {

	@Autowired
	private ITNBUserBookService userBookService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserBook findOneByUserCodeAndBookCode(String userCode, String bookCode) {
		return mapOneIfNotNull(userBookService.findOneByUserCodeAndBookCode(userCode, bookCode), e -> convert(e));
	}

	private UserBook convert(TNBUserBook e) {
		UserBook userBook = new UserBook(e.getUserCode(), e.getBookCode(), e.getBookCount());
		return userBook;
	}

	@Override
	@Transactional
	public void save(UserBook vo) {
		TNBUserBook po = userBookService.findOneByUserCodeAndBookCode(vo.getUserCode(), vo.getBookCode());
		if (null == po) {
			po = new TNBUserBook();
			po.setUserCode(po.getUserCode());
			po.setBookCode(vo.getBookCode());
		}
		po.setBookCount(vo.getBookCount());
		userBookService.save(po);
	}
}
