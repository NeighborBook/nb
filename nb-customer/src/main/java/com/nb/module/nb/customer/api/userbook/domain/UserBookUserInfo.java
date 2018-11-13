package com.nb.module.nb.customer.api.userbook.domain;

import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;

@Data
public class UserBookUserInfo {

	private User user;

	private String bookCode;

	private Integer bookCount;

	private Integer sharable;

	private Integer lentAmount;

	public UserBookUserInfo() {
	}

	public UserBookUserInfo(User user, String bookCode, Integer bookCount, Integer sharable, Integer lentAmount) {

		this.user = user;
		this.bookCode = bookCode;
		this.bookCount = bookCount;
		this.sharable = sharable;
		this.lentAmount = lentAmount;
	}
}
