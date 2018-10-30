package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TagGroupTags {

	@NotBlank
	private String tagGroupCode;

	private List<TagGroupTag> bookTags;

	public TagGroupTags(@NotBlank String tagGroupCode, List<TagGroupTag> bookTags) {
		this.tagGroupCode = tagGroupCode;
		this.bookTags = bookTags;
	}

	public TagGroupTags() {

	}
}
