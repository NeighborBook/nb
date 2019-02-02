package com.nb.module.nb.customer.api.userevent.domain;

import com.nb.module.nb.customer.api.event.domain.Event;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import lombok.Data;

@Data
public class UserEventDetail {

	private User user;

	private Event event;

	private Integer status;

	public UserEventDetail(User user, Event event, Integer status) {
		this.user = user;
		this.event = event;
		this.status = status;
	}

	public UserEventDetail() {

	}
}
