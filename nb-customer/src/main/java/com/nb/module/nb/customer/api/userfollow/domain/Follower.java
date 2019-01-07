package com.nb.module.nb.customer.api.userfollow.domain;

import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import lombok.Data;

@Data
public class Follower {

	private UserFollow userFollow;

	private NearbyUser follower;

	public Follower(UserFollow userFollow, NearbyUser follower) {
		this.userFollow = userFollow;
		this.follower = follower;
	}

	public Follower() {

	}
}
