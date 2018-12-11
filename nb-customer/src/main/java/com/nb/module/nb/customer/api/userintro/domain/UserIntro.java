package com.nb.module.nb.customer.api.userintro.domain;

import lombok.Data;

@Data
public class UserIntro {

	private String userCode;

	private String introUserCode;

	private String source;

	public UserIntro(String userCode, String introUserCode, String source) {
		this.userCode = userCode;
		this.introUserCode = introUserCode;
		this.source = source;
	}

	public UserIntro() {

	}
}
