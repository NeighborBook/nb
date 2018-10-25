package com.nb.module.nb.customer.base.userbook.biz;

import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBUserBookService extends IDataService<TNBUserBook, Integer> {


	TNBUserBook findOneByUserCodeAndBookCode(String userCode, String bookCode);

	List<TNBUserBook> findAllByUserCodeOrderByBookCountDesc(String userCode);

	List<TNBUserBook> findAllByBookCode(String bookCode);

}
