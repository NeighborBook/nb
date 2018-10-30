package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

@Data
public class TagGroupTag {

	private Integer order;

	private Tag tag;

	public TagGroupTag() {
	}

	public TagGroupTag(Integer order, Tag tag) {

		this.order = order;
		this.tag = tag;
	}
}
