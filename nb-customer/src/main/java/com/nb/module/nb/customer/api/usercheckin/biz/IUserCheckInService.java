package com.nb.module.nb.customer.api.usercheckin.biz;

import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.usercheckin.domain.UserCheckIn;

public interface IUserCheckInService {

	/**
	 * 签到
	 *
	 * @param userCheckIn
	 * @return
	 */
	UserBonus checkIn(UserCheckIn userCheckIn);


}
