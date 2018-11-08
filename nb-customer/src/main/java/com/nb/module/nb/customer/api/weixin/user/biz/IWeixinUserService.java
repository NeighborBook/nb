package com.nb.module.nb.customer.api.weixin.user.biz;


import com.zjk.module.common.authorization.client.api.user.domain.User;

public interface IWeixinUserService {

	/**
	 * 通过用户编号查询
	 *
	 * @param userCode
	 * @return
	 */
	User findOneByCode(String userCode);

	String findOpenidByCode(String userCode);

	String findNicknameByCode(String userCode);
}
