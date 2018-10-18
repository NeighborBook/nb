package com.nb.module.nb.customer.api.book.domain;

import lombok.Data;

@Data
public class Author {

	private String author;

	public Author(String author) {
		this.author = author;
	}

	public Author() {

	}
}
