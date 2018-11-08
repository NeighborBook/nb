package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderFormDetail {

	private Date created;

	private Integer orderDetailType;

	private Integer orderDetailStatus;

	public OrderFormDetail(Date created, Integer orderDetailType, Integer orderDetailStatus) {
		this.created = created;
		this.orderDetailType = orderDetailType;
		this.orderDetailStatus = orderDetailStatus;
	}

	public OrderFormDetail() {

	}
}
