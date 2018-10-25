package com.nb.module.nb.customer.api.userbook.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserBook {

	@NotBlank
	private String userCode;

	@NotBlank
	private String bookCode;

	private Integer bookCount;

	public UserBook(@NotBlank String userCode, @NotBlank String bookCode, Integer bookCount) {
		this.userCode = userCode;
		this.bookCode = bookCode;
		this.bookCount = bookCount;
	}

	public UserBook() {

	}
}
