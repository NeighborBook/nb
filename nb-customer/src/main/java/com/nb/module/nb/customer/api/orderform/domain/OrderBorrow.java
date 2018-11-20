package com.nb.module.nb.customer.api.orderform.domain;

import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import lombok.Data;

import java.util.Date;

@Data
public class OrderBorrow {

	private String ownerUserCode;

	private String ownerUserName;

	private String bookCode;

	private String borrowerUserCode;

	private String borrowerUserName;

	private Integer bookCount;

	private Date startBorrowDate;

	private Date initialReturnDate;

	private Date expectedReturnDate;

	private Date actualReturnDate;

	private BookMinInfo bookMinInfo;

	public OrderBorrow(String ownerUserCode, String ownerUserName, String bookCode, String borrowerUserCode, String borrowerUserName, Integer bookCount, Date startBorrowDate, Date initialReturnDate, Date expectedReturnDate, Date actualReturnDate) {
		this.ownerUserCode = ownerUserCode;
		this.ownerUserName = ownerUserName;
		this.bookCode = bookCode;
		this.borrowerUserCode = borrowerUserCode;
		this.borrowerUserName = borrowerUserName;
		this.bookCount = bookCount;
		this.startBorrowDate = startBorrowDate;
		this.initialReturnDate = initialReturnDate;
		this.expectedReturnDate = expectedReturnDate;
		this.actualReturnDate = actualReturnDate;
	}

	public OrderBorrow() {

	}
}
