package com.nb.module.nb.customer.api.userbook.domain;

import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import lombok.Data;

@Data
public class UserBookMinInfo {

	private String userCode;

	private BookMinInfo bookMinInfo;

	private Integer bookCount;

	private Integer sharable;

	private Integer lentAmount;

	public UserBookMinInfo(String userCode, BookMinInfo bookMinInfo, Integer bookCount, Integer sharable, Integer lentAmount) {
		this.userCode = userCode;
		this.bookMinInfo = bookMinInfo;
		this.bookCount = bookCount;
		this.sharable = sharable;
		this.lentAmount = lentAmount;
	}

	public UserBookMinInfo() {

	}
}
