package com.nb.module.nb.customer.api.userbonus.constant;

import java.math.BigDecimal;

public enum UserBonusConstant {

	USER_BONUS_ADJUST(0, "调整", new BigDecimal(0)),

	USER_BONUS_BORROW(1, "借阅申请", new BigDecimal(-110)),

	USER_BONUS_RETURN(2, "归还图书", new BigDecimal(100)),

	USER_BONUS_REGISTER(3, "注册赠送", new BigDecimal(50)),

	USER_BONUS_ADD_BOOK(4, "添加图书", new BigDecimal(5)),

	USER_BONUS_SHARE(5, "分享", new BigDecimal(5)),

	USER_BONUS_INVITE_FRIEND(6, "邀请好友", new BigDecimal(20)),

	USER_BONUS_CHECK_IN(7, "签到", new BigDecimal(2)),

	USER_BONUS_BORROW_AGREE(8, "借阅同意", new BigDecimal(10)),

	USER_BONUS_BORROW_DENY(9, "借阅不同意", new BigDecimal(110));

	private Integer key;

	private String value;

	private BigDecimal bonus;

	UserBonusConstant(Integer key, String value, BigDecimal bonus) {
		this.key = key;
		this.value = value;
		this.bonus = bonus;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public BigDecimal getBonus() {
		return bonus;
	}
}
