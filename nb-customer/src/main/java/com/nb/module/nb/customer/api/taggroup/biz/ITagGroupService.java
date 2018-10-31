package com.nb.module.nb.customer.api.taggroup.biz;

import com.nb.module.nb.customer.api.taggroup.domain.TagGroup;

import java.util.List;

public interface ITagGroupService {

	void save(TagGroup tagGroup);

	List<TagGroup> findAllByVisible(Integer visible);

	void deleteByCode(String tagGroupCode);

	void refresh();
}
