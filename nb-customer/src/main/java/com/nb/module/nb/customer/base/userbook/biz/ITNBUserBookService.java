package com.nb.module.nb.customer.base.userbook.biz;

import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBookCount;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITNBUserBookService extends IDataService<TNBUserBook, Integer> {


	TNBUserBook findOneByUserCodeAndBookCode(String userCode, String bookCode);

	List<TNBUserBook> findAllByUserCodeOrderByBookCountDesc(String userCode);

	Page<TNBUserBook> findAllByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable);

	Page<TNBUserBookCount> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable);

	Long countByUserCode(String userCode);
}
