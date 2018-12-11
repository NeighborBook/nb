package com.nb.module.nb.customer.api.partnerauthorization.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PartnerAuthorization {

	private String code;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String usage;

	public PartnerAuthorization(String code, String username, String password, String usage) {
		this.code = code;
		this.username = username;
		this.password = password;
		this.usage = usage;
	}

	public PartnerAuthorization() {

	}
}
