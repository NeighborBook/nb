package com.nb.module.nb.customer.api.orderform.domain;

import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import lombok.Data;

import java.util.Date;

@Data
public class OrderBorrow {

	private String ownerUserCode;

	private String bookCode;

	private String borrowerUserCode;

	private Integer bookCount;

	private Date startBorrowDate;

	private Date initialReturnDate;

	private Date expectedReturnDate;

	private Date actualReturnDate;

	private BookMinInfo bookMinInfo;

	public OrderBorrow(String ownerUserCode, String bookCode, String borrowerUserCode, Integer bookCount, Date startBorrowDate, Date initialReturnDate, Date expectedReturnDate, Date actualReturnDate) {
		this.ownerUserCode = ownerUserCode;
		this.bookCode = bookCode;
		this.borrowerUserCode = borrowerUserCode;
		this.bookCount = bookCount;
		this.startBorrowDate = startBorrowDate;
		this.initialReturnDate = initialReturnDate;
		this.expectedReturnDate = expectedReturnDate;
		this.actualReturnDate = actualReturnDate;
	}

	public OrderBorrow() {

	}
}
