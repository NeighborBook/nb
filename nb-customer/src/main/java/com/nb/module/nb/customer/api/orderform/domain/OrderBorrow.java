package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderBorrow {

	private String fromUserCode;

	private String bookCode;

	private String toUserCode;

	private Integer bookCount;

	private Date startBorrowDate;

	private Date initialReturnDate;

	private Date expectedReturnDate;

	private Date actualReturnDate;

	public OrderBorrow(String fromUserCode, String bookCode, String toUserCode, Integer bookCount, Date startBorrowDate, Date initialReturnDate, Date expectedReturnDate, Date actualReturnDate) {
		this.fromUserCode = fromUserCode;
		this.bookCode = bookCode;
		this.toUserCode = toUserCode;
		this.bookCount = bookCount;
		this.startBorrowDate = startBorrowDate;
		this.initialReturnDate = initialReturnDate;
		this.expectedReturnDate = expectedReturnDate;
		this.actualReturnDate = actualReturnDate;
	}

	public OrderBorrow() {

	}
}
