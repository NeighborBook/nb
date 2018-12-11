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
	private String purpose;

	public PartnerAuthorization(String code, @NotBlank String username, @NotBlank String password, @NotBlank String purpose) {
		this.code = code;
		this.username = username;
		this.password = password;
		this.purpose = purpose;
	}

	public PartnerAuthorization() {

	}
}
