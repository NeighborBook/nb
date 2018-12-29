package com.nb.module.nb.customer.api.nearby.domain;

import lombok.Data;

@Data
public class NearbyUser {

	private String userCode;

	private Long bookCount;

	public NearbyUser(String userCode, Long bookCount) {
		this.userCode = userCode;
		this.bookCount = bookCount;
	}

	public NearbyUser() {
	}
}
