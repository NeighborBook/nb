package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderFormDetail {

	private Date created;

	private Integer orderDetailType;

	private Integer orderDetailStatus;

	private String remark;

	public OrderFormDetail(Date created, Integer orderDetailType, Integer orderDetailStatus, String remark) {
		this.created = created;
		this.orderDetailType = orderDetailType;
		this.orderDetailStatus = orderDetailStatus;
		this.remark = remark;
	}

	public OrderFormDetail(Integer orderDetailType, Integer orderDetailStatus, String remark) {
		this.orderDetailType = orderDetailType;
		this.orderDetailStatus = orderDetailStatus;
		this.remark = remark;
	}

	public OrderFormDetail() {

	}
}
