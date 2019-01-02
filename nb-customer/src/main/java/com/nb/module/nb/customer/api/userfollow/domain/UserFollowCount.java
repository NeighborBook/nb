package com.nb.module.nb.customer.api.userfollow.domain;

import lombok.Data;

@Data
public class UserFollowCount {

	private Long followers;

	private Long fans;

	public UserFollowCount() {
	}

	public UserFollowCount(Long followers, Long fans) {
		this.followers = followers;
		this.fans = fans;
	}
}
