package com.nb.module.nb.customer.api.login.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResult {

	@ApiModelProperty(value = "用户编号")
	private String code;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "国籍")
	private Integer international;

}
