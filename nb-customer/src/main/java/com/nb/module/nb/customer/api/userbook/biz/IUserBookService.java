package com.nb.module.nb.customer.api.userbook.biz;


import com.nb.module.nb.customer.api.userbook.domain.UserBook;

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
	void save(UserBook vo);


}
