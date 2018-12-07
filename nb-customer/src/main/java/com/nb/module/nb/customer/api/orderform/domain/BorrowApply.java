package com.nb.module.nb.customer.api.orderform.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BorrowApply {

	@NotBlank
	private String ownerUserCode;

	@NotBlank
	private String bookCode;

	@NotBlank
	private String borrowerUserCode;

	private Integer bookCount;

//	private Date startBorrowDate;

	private String remark;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;
}
