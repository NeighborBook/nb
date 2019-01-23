package com.nb.module.nb.customer.api.userevent.domain;

import lombok.Data;

@Data
public class UserEvent {

	String userCode;

	String eventCode;

	public UserEvent(String userCode, String eventCode) {
		this.userCode = userCode;
		this.eventCode = eventCode;
	}

	public UserEvent() {

	}
}
