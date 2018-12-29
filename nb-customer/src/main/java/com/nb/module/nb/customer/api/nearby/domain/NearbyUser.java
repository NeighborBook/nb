package com.nb.module.nb.customer.api.nearby.domain;

import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class NearbyUser {

	private User user;

	private Long bookCount;

	private Page<UserBookMinInfo> userBookMinInfos;

	public NearbyUser(User user, Long bookCount, Page<UserBookMinInfo> userBookMinInfos) {
		this.user = user;
		this.bookCount = bookCount;
		this.userBookMinInfos = userBookMinInfos;
	}

	public NearbyUser() {
	}
}
