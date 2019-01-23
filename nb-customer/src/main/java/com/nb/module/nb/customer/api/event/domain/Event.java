package com.nb.module.nb.customer.api.event.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

	private String code;

	private String name;

	private Date beginDate;

	private Date endDate;

	private String location;

	private Date enterBeginDate;

	private Date enterEndDate;

	private Integer quota;

	private Integer maxQuota;

	private Integer signUp;

	private Integer surplus;

	public Event(String code, String name, Date beginDate, Date endDate, String location, Date enterBeginDate, Date enterEndDate, Integer quota, Integer maxQuota, Integer signUp, Integer surplus) {
		this.code = code;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.location = location;
		this.enterBeginDate = enterBeginDate;
		this.enterEndDate = enterEndDate;
		this.quota = quota;
		this.maxQuota = maxQuota;
		this.signUp = signUp;
		this.surplus = surplus;
	}

	public Event() {
	}
}
