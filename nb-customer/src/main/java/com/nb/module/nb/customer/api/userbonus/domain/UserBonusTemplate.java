package com.nb.module.nb.customer.api.userbonus.domain;

import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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

	public UserBonusTemplate(@Validated BaseUserBonus baseUserBonus, UserBonusConstant userBonusConstant) {
		this.updated = baseUserBonus.getUpdated();
		this.userCode = baseUserBonus.getUserCode();
		this.userBonusConstant = userBonusConstant;
		this.remark = baseUserBonus.getRemark();
	}

	public UserBonusTemplate(@Validated BaseUserBonus baseUserBonus, UserBonusConstant userBonusConstant, BigDecimal extraBonus) {
		this.updated = baseUserBonus.getUpdated();
		this.userCode = baseUserBonus.getUserCode();
		this.userBonusConstant = userBonusConstant;
		if (null != extraBonus) {
			this.extraBonus = extraBonus;
		}
		this.remark = baseUserBonus.getRemark();
	}

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
