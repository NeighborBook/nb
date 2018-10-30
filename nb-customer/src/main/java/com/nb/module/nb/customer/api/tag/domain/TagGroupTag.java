package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

@Data
public class TagGroupTag {

	private Integer position;

	private Tag tag;

	public TagGroupTag() {
	}

	public TagGroupTag(Integer position, Tag tag) {
		this.position = position;
		this.tag = tag;
	}
}
