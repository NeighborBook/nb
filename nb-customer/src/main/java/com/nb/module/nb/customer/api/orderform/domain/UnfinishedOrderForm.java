package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

@Data
public class UnfinishedOrderForm<T> {

	private Long days;

	private OrderForm<T> orderForm;

	public UnfinishedOrderForm() {
	}

	public UnfinishedOrderForm(Long days, OrderForm<T> orderForm) {

		this.days = days;
		this.orderForm = orderForm;
	}
}
