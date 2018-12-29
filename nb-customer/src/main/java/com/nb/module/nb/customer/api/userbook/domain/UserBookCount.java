package com.nb.module.nb.customer.api.userbook.domain;

import lombok.Data;

@Data
public class UserBookCount {

	private String userCode;

	private Long bookCount;

	public UserBookCount() {
	}

	public UserBookCount(String userCode, Long bookCount) {

		this.userCode = userCode;
		this.bookCount = bookCount;
	}
}
