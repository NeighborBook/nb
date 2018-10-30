package com.nb.module.nb.customer.api.tag.biz;


import com.nb.module.nb.customer.api.tag.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITagService {

	Tag findOneByCode(String code);

	Tag findOneByName(String name);

	Page<Tag> findAll(Pageable pageable);

	List<BookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode);

	List<TagGroupTag> findAllByTagGroupCodeOrderByOrder(String tagGroupCode);

	/**
	 * 保存（不更新）
	 *
	 * @param tag
	 */
	void save(Tag tag);

	/**
	 * 保存书标签
	 *
	 * @param bookTags
	 */
	void saveBookTags(BookTags bookTags);

	/**
	 * 保存书标签
	 *
	 * @param bookCode
	 * @param bookTags
	 */
	void saveBookTags(String bookCode, List<BookTag> bookTags);

	/**
	 * 保存标签组标签
	 *
	 * @param tagGroupTags
	 */
	void saveTagGroupTags(TagGroupTags tagGroupTags);

	/**
	 * 保存标签组标签
	 *
	 * @param tagCode
	 * @param tagGroupTags
	 */
	void saveTagGroupTags(String tagCode, List<TagGroupTag> tagGroupTags);

}
