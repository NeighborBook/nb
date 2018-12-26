package com.nb.module.nb.customer.api.weixin.user.domain;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserLocation {

	@NotBlank
	private String userCode;

	@NotNull
	@Valid
	private Location location;

	@NotBlank
	private String tagCode;

	public UserLocation(@NotBlank String userCode, @NotNull @Valid Location location, @NotBlank String tagCode) {
		this.userCode = userCode;
		this.location = location;
		this.tagCode = tagCode;
	}

	public UserLocation() {

	}
}
