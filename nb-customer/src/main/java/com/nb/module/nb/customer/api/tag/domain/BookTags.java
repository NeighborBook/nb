package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

import java.util.List;

@Data
public class BookTags {

	private String bookCode;

	private List<BookTag> bookTags;

	public BookTags(String bookCode, List<BookTag> bookTags) {
		this.bookCode = bookCode;
		this.bookTags = bookTags;
	}

	public BookTags() {

	}
}
