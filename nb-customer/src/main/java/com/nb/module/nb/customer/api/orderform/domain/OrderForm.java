package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderForm {

	private Date created;

	private Date updated;

	private String code;

	private Integer orderType;

	private Integer orderStatus;

}
