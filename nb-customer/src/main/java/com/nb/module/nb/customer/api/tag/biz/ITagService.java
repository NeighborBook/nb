package com.nb.module.nb.customer.api.tag.biz;


import com.nb.module.nb.customer.api.tag.domain.BookTag;
import com.nb.module.nb.customer.api.tag.domain.BookTags;
import com.nb.module.nb.customer.api.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITagService {

	Tag findOneByCode(String code);

	Tag findOneByName(String name);

	Page<Tag> findAll(Pageable pageable);

	List<BookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode);

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
}
