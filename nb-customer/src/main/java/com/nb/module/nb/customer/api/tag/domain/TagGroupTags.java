package com.nb.module.nb.customer.api.tag.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TagGroupTags {

	@NotBlank
	private String tagGroupCode;

	private List<TagGroupTag> tagGroupTags;

	public TagGroupTags(@NotBlank String tagGroupCode, List<TagGroupTag> tagGroupTags) {
		this.tagGroupCode = tagGroupCode;
		this.tagGroupTags = tagGroupTags;
	}

	public TagGroupTags() {

	}
}
