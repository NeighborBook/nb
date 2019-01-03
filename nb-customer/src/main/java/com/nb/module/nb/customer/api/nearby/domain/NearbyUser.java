package com.nb.module.nb.customer.api.nearby.domain;

import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
import com.nb.module.nb.customer.api.weixin.user.domain.UserLocation;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class NearbyUser {

	private User user;

	private List<UserLocation> userLocations;

	private Long bookCount;

	private Page<UserBookMinInfo> userBookMinInfos;

	private Boolean followed;

	public NearbyUser(User user, List<UserLocation> userLocations, Long bookCount, Page<UserBookMinInfo> userBookMinInfos, Boolean followed) {
		this.user = user;
		this.userLocations = userLocations;
		this.bookCount = bookCount;
		this.userBookMinInfos = userBookMinInfos;
		this.followed = followed;
	}

	public NearbyUser() {
	}
}
