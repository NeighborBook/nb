package com.nb.module.nb.customer.base.userbook.domain;

import lombok.Data;

@Data
public class TNBUserBookCount {

	private String userCode;

	private Long bookCount;

	public TNBUserBookCount(String userCode, Long bookCount) {
		this.userCode = userCode;
		this.bookCount = bookCount;
	}

	public TNBUserBookCount() {

	}
}
