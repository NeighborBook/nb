package com.nb.module.nb.customer.api.weixin.user.biz;


import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.nb.module.nb.customer.api.weixin.user.domain.UserLocation;
import com.zjk.module.common.authorization.client.api.user.domain.User;

import java.util.List;

public interface IWeixinUserService {

	/**
	 * 通过用户编号查询
	 *
	 * @param userCode
	 * @return
	 */
	User findOneByCode(String userCode);

	User findFullOneByCode(String userCode);

	User findFullOneWithPassowrdByCode(String userCode);

	String findOpenidByCode(String userCode);

	String findNicknameByCode(String userCode);

	User updateUser(User user);

	User updateMobile(Mobile mobile);

	void saveUserLocation(UserLocation userLocation);

	void deleteUserLocation(UserLocation userLocation);

	List<UserLocation> findUserLocationByCode(String userCode);
}
