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

}
