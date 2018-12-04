package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserBonusDetail {

	private String code;

	private Date created;

	private String userCode;

	private Integer type;

	private BigDecimal bonus;

	private String remark;

	public UserBonusDetail(String code, Date created, String userCode, Integer type, BigDecimal bonus, String remark) {
		this.code = code;
		this.created = created;
		this.userCode = userCode;
		this.type = type;
		this.bonus = bonus;
		this.remark = remark;
	}

	public UserBonusDetail() {

	}
}
