package com.nb.module.nb.customer.api.userfollow.domain;

import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserFollow {

	@NotBlank
	private String userCode;

	private User user;

	@NotBlank
	private String followUserCode;

	private User followUser;

	@NotBlank
	private String source;

	public UserFollow(@NotBlank String userCode, @NotBlank String followUserCode, @NotBlank String source) {
		this.userCode = userCode;
		this.followUserCode = followUserCode;
		this.source = source;
	}

	public UserFollow() {

	}
}
