package com.nb.module.nb.customer.api.userbonus.domain;

import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserBonusTemplate {

	@NotNull
	private Date updated;

	private String userCode;

	private UserBonusConstant userBonusConstant;

	private BigDecimal extraBonus = BigDecimal.ZERO;

	private String remark;

	public UserBonusTemplate(@NotNull Date updated, String userCode, UserBonusConstant userBonusConstant, BigDecimal extraBonus, String remark) {
		this.updated = updated;
		this.userCode = userCode;
		this.userBonusConstant = userBonusConstant;
		if (null != extraBonus) {
			this.extraBonus = extraBonus;
		}
		this.remark = remark;
	}

	public UserBonusTemplate() {
	}
}
