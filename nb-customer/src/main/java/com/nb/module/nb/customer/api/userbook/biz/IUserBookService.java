package com.nb.module.nb.customer.api.userbook.biz;


import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.nb.module.nb.customer.api.userbook.domain.UserBookCount;
import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
import com.nb.module.nb.customer.api.userbook.domain.UserBookUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface IUserBookService {

	/**
	 * 通过userCode和bookCode获取用户图书信息
	 *
	 * @param userCode
	 * @param bookCode
	 * @return
	 */
	UserBook findOneByUserCodeAndBookCode(String userCode, String bookCode);

	/**
	 * 保存
	 *
	 * @param vo
	 */
	UserBonus save(UserBook vo);

	Page<UserBook> findAllByBookCodeAndSharable(String bookCode, Integer sharable, @PageableDefault Pageable pageable);

	Page<UserBookMinInfo> findAllByTagCodeAndUserCode(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable);

	Page<UserBookMinInfo> findAllBySearchAndUserCode(String search, String userCode, Pageable pageable);

	Page<UserBookUserInfo> findAllUserInfoByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable);

	Page<UserBookCount> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable);

}
