package com.nb.module.nb.customer.api.usersharedetail.biz.impl;

import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusTemplate;
import com.nb.module.nb.customer.api.usersharedetail.biz.IUserShareService;
import com.nb.module.nb.customer.api.usersharedetail.domain.UserShare;
import com.nb.module.nb.customer.base.usersharedetail.biz.ITNBUserShareService;
import com.nb.module.nb.customer.base.usersharedetail.domain.TNBUserShare;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserShareServiceImpl extends CommonServiceImpl implements IUserShareService {

	@Autowired
	private ITNBUserShareService userShareService;

	@Autowired
	private IUserBonusService userBonusService;

	@Override
	@Transactional
	public UserBonus share(UserShare userShare) {
		UserBonus userBonus = null;
		// 查询所有记录
		List<TNBUserShare> pos = userShareService.findAllByUserCodeAndShareDate(userShare.getBaseUserBonus().getUserCode(), userShare.getShareDate());
		// 保存本次分享
		TNBUserShare po = userShareService.newInstance();
		po.setUserCode(userShare.getBaseUserBonus().getUserCode());
		po.setShareDate(userShare.getShareDate());
		userShareService.save(po);
		// 如果当天没有分享记录，则加积分
		if (null == pos || pos.isEmpty()) {
			userShare.getBaseUserBonus().setBizCode(po.getCode());
			userBonus = userBonusService.operate(new UserBonusTemplate(userShare.getBaseUserBonus(), UserBonusConstant.USER_BONUS_SHARE));
		}
		return userBonus;
	}
}
