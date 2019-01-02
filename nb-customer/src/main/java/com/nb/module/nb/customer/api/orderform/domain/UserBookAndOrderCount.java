package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

@Data
public class UserBookAndOrderCount {

	Long userBooks;

	Long borrowers;

	Long owners;

	public UserBookAndOrderCount() {
	}

	public UserBookAndOrderCount(Long userBooks, Long borrowers, Long owners) {

		this.userBooks = userBooks;
		this.borrowers = borrowers;
		this.owners = owners;
	}
}
