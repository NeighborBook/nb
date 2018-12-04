package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserBonus {

	private String userCode;

	@NotNull
	private Date updated;

	private BigDecimal totalBonus;

	private BigDecimal currentBonus;

	private UserBonusDetail currentUserBonusDetail;

	public UserBonus(String userCode, @NotNull Date updated, BigDecimal totalBonus, BigDecimal currentBonus) {
		this.userCode = userCode;
		this.updated = updated;
		this.totalBonus = totalBonus;
		this.currentBonus = currentBonus;
	}

	public UserBonus() {

	}
}
