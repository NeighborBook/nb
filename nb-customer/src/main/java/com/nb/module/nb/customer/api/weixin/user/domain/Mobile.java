package com.nb.module.nb.customer.api.weixin.user.domain;

import com.zjk.module.common.authorization.client.api.user.constant.UserConstant;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Mobile extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String userCode;

	@ApiModelProperty(value = "手机")
	@NotBlank
	private String mobile;

	@ApiModelProperty(value = "手机是否验证")
	private Integer mobileVerified = UserConstant.VERIFIED_0;

}
