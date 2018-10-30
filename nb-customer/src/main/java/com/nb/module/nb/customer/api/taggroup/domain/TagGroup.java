package com.nb.module.nb.customer.api.taggroup.domain;

import com.nb.module.nb.customer.api.tag.domain.TagGroupTag;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TagGroup {

	private String code;

	@NotBlank
	private String name;

	private String title;

	private Integer visible;

	private Integer position;

	private List<TagGroupTag> tagGroupTags;

	public TagGroup() {
	}

	public TagGroup(String code, @NotBlank String name, String title, Integer visible, Integer position) {
		this.code = code;
		this.name = name;
		this.title = title;
		this.visible = visible;
		this.position = position;
	}
}
