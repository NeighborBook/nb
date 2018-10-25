package com.nb.module.nb.customer.api.userbook.domain;

import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import lombok.Data;

@Data
public class UserBookMinInfo {

	private String userCode;

	private BookMinInfo bookMinInfo;

	private Integer bookCount;

	public UserBookMinInfo(String userCode, BookMinInfo bookMinInfo, Integer bookCount) {
		this.userCode = userCode;
		this.bookMinInfo = bookMinInfo;
		this.bookCount = bookCount;
	}

	public UserBookMinInfo() {

	}
}
