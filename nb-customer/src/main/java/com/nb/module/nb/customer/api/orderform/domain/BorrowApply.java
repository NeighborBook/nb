package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class BorrowApply {

	@NotBlank
	private String fromUserCode;

	@NotBlank
	private String bookCode;

	@NotBlank
	private String toUserCode;

	private Integer bookCount;

	private Date startBorrowDate;

	private String remark;
}
