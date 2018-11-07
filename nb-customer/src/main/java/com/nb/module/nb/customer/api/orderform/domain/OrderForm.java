package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderForm<T> {

	private Date created;

	private Date updated;

	private String code;

	private Integer orderType;

	private Integer orderStatus;

	private List<OrderFormDetail> details;

	private T order;

}
