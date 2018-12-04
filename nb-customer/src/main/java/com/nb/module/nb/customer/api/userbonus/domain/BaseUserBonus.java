package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class BaseUserBonus {

	@NotNull
	private Date updated;

	@NotBlank
	private String userCode;

	private String remark;

	private String bizCode;

	public BaseUserBonus() {
	}

	public BaseUserBonus(@NotNull Date updated, @NotBlank String userCode, String remark, String bizCode) {

		this.updated = updated;
		this.userCode = userCode;
		this.remark = remark;
		this.bizCode = bizCode;
	}
}
