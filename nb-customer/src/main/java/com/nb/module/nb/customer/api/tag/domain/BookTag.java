package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

@Data
public class BookTag {

	private Integer tagCount;

	private Tag tag;

	public BookTag(Integer tagCount, Tag tag) {
		this.tagCount = tagCount;
		this.tag = tag;
	}

	public BookTag() {

	}
}
