package com.nb.module.nb.customer.api.userbook.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserBook {

	@NotBlank
	private String userCode;

	@NotBlank
	private String bookCode;

	private Integer bookCount;

	private Integer sharable;

	private Integer lentAmount;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;

	public UserBook(@NotBlank String userCode, @NotBlank String bookCode, Integer bookCount, Integer sharable, Integer lentAmount) {
		this.userCode = userCode;
		this.bookCode = bookCode;
		this.bookCount = bookCount;
		this.sharable = sharable;
		this.lentAmount = lentAmount;
	}

	public UserBook() {

	}
}
