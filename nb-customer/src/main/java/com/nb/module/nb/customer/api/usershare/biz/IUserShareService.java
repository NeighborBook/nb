package com.nb.module.nb.customer.api.usershare.biz;

import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.usershare.domain.UserShare;

public interface IUserShareService {

	/**
	 * 分享
	 *
	 * @param userShare
	 * @return
	 */
	UserBonus share(UserShare userShare);


}
