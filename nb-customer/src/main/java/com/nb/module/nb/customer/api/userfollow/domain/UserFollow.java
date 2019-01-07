package com.nb.module.nb.customer.api.userfollow.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserFollow {

	@NotBlank
	private String userCode;

	@NotBlank
	private String followUserCode;

	private String source;

	public UserFollow(@NotBlank String userCode, @NotBlank String followUserCode, String source) {
		this.userCode = userCode;
		this.followUserCode = followUserCode;
		this.source = source;
	}

	public UserFollow() {

	}
}
