package com.nb.module.nb.customer.api.book.domain;

import lombok.Data;

@Data
public class Translator {

	private String translator;

	public Translator(String translator) {
		this.translator = translator;
	}

	public Translator() {

	}
}
