package com.nb.module.nb.customer.api.nearby.domain;

import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class NearbyUser {

	private User user;

	private Page<UserBookMinInfo> userBookMinInfos;

	private Boolean followed;

	public NearbyUser(User user, Page<UserBookMinInfo> userBookMinInfos, Boolean followed) {
		this.user = user;
		this.userBookMinInfos = userBookMinInfos;
		this.followed = followed;
	}

	public NearbyUser() {
	}
}
