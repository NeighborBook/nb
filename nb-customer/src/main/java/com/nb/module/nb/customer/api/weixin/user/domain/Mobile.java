package com.nb.module.nb.customer.api.weixin.user.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Mobile extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String userCode;

	@ApiModelProperty(value = "手机")
	@NotBlank
	private String mobile;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;
}
