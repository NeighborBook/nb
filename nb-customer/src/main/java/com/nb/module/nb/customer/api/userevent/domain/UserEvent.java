package com.nb.module.nb.customer.api.userevent.domain;

import lombok.Data;

@Data
public class UserEvent {

	private String userCode;

	private String eventCode;

	private Integer status;

	public UserEvent(String userCode, String eventCode, Integer status) {
		this.userCode = userCode;
		this.eventCode = eventCode;
		this.status = status;
	}

	public UserEvent() {

	}
}
