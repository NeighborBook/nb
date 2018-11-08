package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class OrderBorrow {

	@NotBlank
	private String fromUserCode;

	@NotBlank
	private String bookCode;

	@NotBlank
	private String toUserCode;

	private Integer count;

	private Date startBorrowDate;

	private Date initialReturnDate;

	private Date expectedReturnDate;

	private Date actualReturnDate;

	public OrderBorrow(@NotBlank String fromUserCode, @NotBlank String bookCode, @NotBlank String toUserCode, Integer count, Date startBorrowDate, Date initialReturnDate, Date expectedReturnDate, Date actualReturnDate) {
		this.fromUserCode = fromUserCode;
		this.bookCode = bookCode;
		this.toUserCode = toUserCode;
		this.count = count;
		this.startBorrowDate = startBorrowDate;
		this.initialReturnDate = initialReturnDate;
		this.expectedReturnDate = expectedReturnDate;
		this.actualReturnDate = actualReturnDate;
	}

	public OrderBorrow() {

	}
}
