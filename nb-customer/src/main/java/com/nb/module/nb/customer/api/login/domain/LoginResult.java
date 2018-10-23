package com.nb.module.nb.customer.api.login.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

	@ApiModelProperty(value = "插件")
	private Map<String, Object> plugin = new HashMap();

	public LoginResult(String code, String email, String mobile, Integer lang, Integer international, Map<String, Object> plugin) {
		this.code = code;
		this.email = email;
		this.mobile = mobile;
		this.lang = lang;
		this.international = international;
		if (null != plugin) {
			this.plugin = plugin;
		}
	}

	public LoginResult() {

	}
}
