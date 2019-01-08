package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

@Data
public class UserBookAndOrderCount {

	private Long userBooks;

	private OrderCount borrowers;

	private OrderCount owners;

	public UserBookAndOrderCount() {
	}

	public UserBookAndOrderCount(Long userBooks, OrderCount borrowers, OrderCount owners) {
		this.userBooks = userBooks;
		this.borrowers = borrowers;
		this.owners = owners;
	}
}
