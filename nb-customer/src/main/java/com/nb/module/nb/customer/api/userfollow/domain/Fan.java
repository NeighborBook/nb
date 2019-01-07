package com.nb.module.nb.customer.api.userfollow.domain;

import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import lombok.Data;

@Data
public class Fan {

	private UserFollow userFollow;

	private NearbyUser fan;

	public Fan(UserFollow userFollow, NearbyUser fan) {
		this.userFollow = userFollow;
		this.fan = fan;
	}

	public Fan() {

	}
}
