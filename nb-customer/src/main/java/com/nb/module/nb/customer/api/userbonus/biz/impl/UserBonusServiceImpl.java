package com.nb.module.nb.customer.api.userbonus.biz.impl;

import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.*;
import com.nb.module.nb.customer.api.userbonus.exception.UserBonusCode;
import com.nb.module.nb.customer.base.userbonus.biz.ITNBUserBonusService;
import com.nb.module.nb.customer.base.userbonus.domain.TNBUserBonus;
import com.nb.module.nb.customer.base.userbonusdetail.biz.ITNBUserBonusDetailService;
import com.nb.module.nb.customer.base.userbonusdetail.domain.TNBUserBonusDetail;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserBonusServiceImpl extends CommonServiceImpl implements IUserBonusService {

	@Autowired
	private ITNBUserBonusService userBonusService;

	@Autowired
	private ITNBUserBonusDetailService userBonusDetailService;

	public static final String USER_BONUS = "user_bonus";

	private UserBonus checkUserBonus(UserBonusTemplate userBonusTemplate) {
		// 用户积分不存在，请初始化
		UserBonus userBonus = checkIfNullThrowException(findOneUserBonusByUserCode(userBonusTemplate.getUserCode()), new BusinessException(UserBonusCode.UB0002, new Object[]{userBonusTemplate.getUserCode()}));
		// 积分已经更新，请刷新页面
		if (userBonus.getUpdated().getTime() != userBonusTemplate.getUpdated().getTime()) {
			throw new BusinessException(UserBonusCode.UB0001, new Object[]{userBonusTemplate.getUserCode()});
		}
		return userBonus;
	}

	/**************************************************************************************************************************************************************/

	private UserBonusDetail processUserBonus(UserBonus userBonus, UserBonusTemplate userBonusTemplate) {
		// 真实积分=模板积分+额外积分（可以是负数）
		BigDecimal realBonus = userBonusTemplate.getUserBonusConstant().getBonus().add(userBonusTemplate.getExtraBonus());
		// 当真实积分大于0的时候，加入总计积分
		if (1 == realBonus.compareTo(BigDecimal.ZERO)) {
			userBonus.setTotalBonus(userBonus.getTotalBonus().add(realBonus));
		}
		// 当前积分
		BigDecimal currentBonus = userBonus.getCurrentBonus().add(realBonus);
		// 当前积分比0小则提示积分不足
		if (-1 == currentBonus.compareTo(BigDecimal.ZERO)) {
			throw new BusinessException(UserBonusCode.UB0003, new Object[]{userBonusTemplate.getUserCode(), userBonus.getCurrentBonus(), realBonus});
		}
		userBonus.setCurrentBonus(currentBonus);
		return new UserBonusDetail(null, null, userBonus.getUserCode(), userBonusTemplate.getUserBonusConstant().getKey(), realBonus, userBonusTemplate.getRemark(), userBonusTemplate.getBizCode());
	}

	/**************************************************************************************************************************************************************/

	private void saveUserBonus(UserBonus userBonus) {
		TNBUserBonus po = userBonusService.findOneByUserCode(userBonus.getUserCode());
		if (null == po) {
			po = new TNBUserBonus();
			po.setUserCode(userBonus.getUserCode());
		}
		po.setTotalBonus(userBonus.getTotalBonus());
		po.setCurrentBonus(userBonus.getCurrentBonus());
		userBonusService.save(po);

		userBonus.setUpdated(po.getUpdated());
	}

	private void saveUserBonusDetail(UserBonusDetail userBonusDetail) {
		TNBUserBonusDetail po = userBonusDetailService.newInstance();
		po.setUserCode(userBonusDetail.getUserCode());
		po.setType(userBonusDetail.getType());
		po.setBonus(userBonusDetail.getBonus());
		po.setRemark(userBonusDetail.getRemark());
		po.setBizCode(userBonusDetail.getBizCode());
		userBonusDetailService.save(po);

		userBonusDetail.setCode(po.getCode());
		userBonusDetail.setCreated(po.getCreated());
	}

	/**************************************************************************************************************************************************************/

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserBonus findOneUserBonusByUserCode(String userCode) {
		return mapOneIfNotNull(userBonusService.findOneByUserCode(userCode), e -> new UserBonus(e.getUserCode(), e.getUpdated(), e.getTotalBonus(), e.getCurrentBonus()));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBonusDetail> findAllUserBonusDetailByUserCode(String userCode, Pageable pageable) {
		return userBonusDetailService.findAllByUserCode(userCode, pageable).map(e -> new UserBonusDetail(e.getCode(), e.getCreated(), e.getUserCode(), e.getType(), e.getBonus(), e.getRemark(), e.getBizCode()));
	}

	@Override
	@Transactional
	public BaseUserBonus findOneBaseUserBonusByUserCode(String userCode) {
		return mapOneIfNotNull(findOneOrInitByUserCode(userCode), e -> new BaseUserBonus(e.getUpdated(), e.getUserCode(), null, null));
	}

	/**************************************************************************************************************************************************************/

	@Override
	@Transactional
	public UserBonus findOneOrInitByUserCode(String userCode) {
		return checkIfNullNewInstance(findOneUserBonusByUserCode(userCode), e -> {
			UserBonus userBonus = new UserBonus(userCode, null, BigDecimal.ZERO, BigDecimal.ZERO);
			saveUserBonus(userBonus);
			return userBonus;
		});
	}

	@Override
	@Transactional
	public UserBonus adjust(Adjust adjust) {
		return operate(new UserBonusTemplate(adjust.getBaseUserBonus(), UserBonusConstant.USER_BONUS_ADJUST, adjust.getExtraBonus()));
	}

	@Override
	@Transactional
	public UserBonus operate(UserBonusTemplate userBonusTemplate) {
		// 校验积分
		UserBonus userBonus = checkUserBonus(userBonusTemplate);
		// 处理积分
		UserBonusDetail userBonusDetail = processUserBonus(userBonus, userBonusTemplate);
		userBonus.setCurrentUserBonusDetail(userBonusDetail);
		// 保存userBonus
		saveUserBonus(userBonus);
		// 保存userBonusDetail;
		saveUserBonusDetail(userBonusDetail);
		return userBonus;
	}

	@Override
	public User addUserBonusToUser(User user, UserBonus userBonus) {
		user.getPlugin().put(USER_BONUS, userBonus);
		return user;
	}
}
