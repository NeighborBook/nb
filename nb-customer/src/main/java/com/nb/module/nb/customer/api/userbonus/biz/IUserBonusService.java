package com.nb.module.nb.customer.api.userbonus.biz;

import com.nb.module.nb.customer.api.userbonus.domain.*;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserBonusService {


	UserBonus findOneUserBonusByUserCode(String userCode);

	Page<UserBonusDetail> findAllUserBonusDetailByUserCode(String userCode, Pageable pageable);

	/**
	 * 根据用户编号查找积分用户积分
	 *
	 * @param userCode
	 * @return
	 */
	BaseUserBonus findOneBaseUserBonusByUserCode(String userCode);

	/**
	 * 查询积分，不存在则初始化
	 *
	 * @param userCode
	 * @return
	 */
	UserBonus findOneOrInitByUserCode(String userCode);

	/**
	 * 调整积分
	 *
	 * @param adjust
	 */
	UserBonus adjust(Adjust adjust);

	/**
	 * 操作积分
	 *
	 * @param userBonusTemplate
	 */
	UserBonus operate(UserBonusTemplate userBonusTemplate);

	/**
	 * 把UserBonus加入user
	 *
	 * @param user
	 * @param userBonus
	 * @return
	 */
	User addUserBonusToUser(User user, UserBonus userBonus);

}
