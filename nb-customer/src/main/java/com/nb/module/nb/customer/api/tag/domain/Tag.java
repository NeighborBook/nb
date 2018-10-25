package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Tag {

	private String code;

	@NotBlank
	private String name;

	private String title;

	public Tag() {
	}

	public Tag(String code, String name, String title) {

		this.code = code;
		this.name = name;
		this.title = title;
	}
}
