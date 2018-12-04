package com.nb.module.nb.customer.api.usercheckin.biz.impl;

import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusTemplate;
import com.nb.module.nb.customer.api.usercheckin.biz.IUserCheckInService;
import com.nb.module.nb.customer.api.usercheckin.domain.UserCheckIn;
import com.nb.module.nb.customer.api.usercheckin.exception.UserCheckInCode;
import com.nb.module.nb.customer.base.usercheckin.biz.ITNBUserCheckInService;
import com.nb.module.nb.customer.base.usercheckin.domain.TNBUserCheckIn;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCheckInServiceImpl extends CommonServiceImpl implements IUserCheckInService {

	@Autowired
	private ITNBUserCheckInService userCheckInService;

	@Autowired
	private IUserBonusService userBonusService;

	@Override
	@Transactional
	public UserBonus checkIn(UserCheckIn userCheckIn) {
		TNBUserCheckIn po = userCheckInService.findOneByUserCodeAndCheckIn(userCheckIn.getBaseUserBonus().getUserCode(), userCheckIn.getCheckIn());
		if (null != po) {
			throw new BusinessException(UserCheckInCode.UCI0001, new Object[]{userCheckIn.getBaseUserBonus().getUserCode(), userCheckIn.getCheckIn()});
		} else {
			po = userCheckInService.newInstance();
			po.setUserCode(userCheckIn.getBaseUserBonus().getUserCode());
			po.setCheckIn(userCheckIn.getCheckIn());
			userCheckInService.save(po);

			userCheckIn.getBaseUserBonus().setBizCode(po.getCode());
		}
		return userBonusService.operate(new UserBonusTemplate(userCheckIn.getBaseUserBonus(), UserBonusConstant.USER_BONUS_CHECK_IN));
	}
}
