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

	private String bizCode;

	public UserBonusTemplate(@Validated BaseUserBonus baseUserBonus, UserBonusConstant userBonusConstant) {
		this(baseUserBonus, userBonusConstant, BigDecimal.ZERO);
	}

	public UserBonusTemplate(@Validated BaseUserBonus baseUserBonus, UserBonusConstant userBonusConstant, BigDecimal extraBonus) {
		this(baseUserBonus.getUpdated(), baseUserBonus.getUserCode(), userBonusConstant, extraBonus, baseUserBonus.getRemark(), baseUserBonus.getBizCode());
	}

	public UserBonusTemplate(@NotNull Date updated, String userCode, UserBonusConstant userBonusConstant, BigDecimal extraBonus, String remark, String bizCode) {
		this.updated = updated;
		this.userCode = userCode;
		this.userBonusConstant = userBonusConstant;
		if (null != extraBonus) {
			this.extraBonus = extraBonus;
		}
		this.remark = remark;
		this.bizCode = bizCode;
	}

	public UserBonusTemplate() {
	}
}
