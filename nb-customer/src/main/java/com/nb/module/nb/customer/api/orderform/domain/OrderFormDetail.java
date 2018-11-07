package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderFormDetail {

	private Date created;

	private Integer orderDetailType;

	private Integer orderDetailStatus;

}
