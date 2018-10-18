package com.nb.module.nb.customer.api.tag.biz.impl;

import com.nb.module.nb.customer.api.tag.biz.ITagService;
import com.nb.module.nb.customer.api.tag.domain.BookTag;
import com.nb.module.nb.customer.api.tag.domain.BookTags;
import com.nb.module.nb.customer.api.tag.domain.Tag;
import com.nb.module.nb.customer.base.booktag.biz.ITNBBookTagService;
import com.nb.module.nb.customer.base.booktag.domain.TNBBookTag;
import com.nb.module.nb.customer.base.tag.biz.ITNBTagService;
import com.nb.module.nb.customer.base.tag.domain.TNBTag;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl extends CommonServiceImpl implements ITagService {

	@Autowired
	private ITNBTagService tagService;
	@Autowired
	private ITNBBookTagService bookTagService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Tag findOneByCode(String code) {
		return mapOneIfNotNull(tagService.findOneByCode(code), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Tag findOneByName(String name) {
		return mapOneIfNotNull(tagService.findOneByCode(name), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Tag> findAll(Pageable pageable) {
		return tagService.findAll(pageable).map(e -> convert(e));
	}

	private Tag convert(TNBTag e) {
		return new Tag(e.getCode(), e.getName(), e.getTitle());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<BookTag> findAllByBookCodeOrderByTagCountDesc(String bookCode) {
		return map(bookTagService.findAllByBookCodeOrderByTagCountDesc(bookCode), s -> new BookTag(s.getTagCount(), findOneByCode(s.getTagCode())));
	}

	@Override
	@Transactional
	public void save(Tag tag) {
		TNBTag po = tagService.findOneByName(tag.getName());
		if (null == po) {
			po = tagService.newInstance();
			po.setName(tag.getName());
			po.setTitle(tag.getTitle());
			tagService.save(po);
		}
		tag.setCode(po.getCode());
	}

	@Override
	@Transactional
	public void saveBookTags(BookTags bookTags) {
		bookTags.getBookTags().forEach(e -> {
			// 保存Tag
			Tag tag = e.getTag();
			if (StringUtils.isBlank(tag.getCode())) {
				save(tag);
			}
			// 书标签关联
			TNBBookTag po = bookTagService.findOneByBookCodeAndTagCode(bookTags.getBookCode(), tag.getCode());
			if (null == po) {
				po = new TNBBookTag();
				po.setBookCode(bookTags.getBookCode());
				po.setTagCode(e.getTag().getCode());
				// 没有tagCount则默认1
				if (null == e.getTagCount() || 0 == e.getTagCount()) {
					e.setTagCount(1);
				}
				po.setTagCount(e.getTagCount());
			} else {
				po.setTagCount(po.getTagCount() + 1);
			}
			bookTagService.save(po);
		});
	}

	@Override
	@Transactional
	public void saveBookTags(String bookCode, List<BookTag> bookTags) {
		saveBookTags(new BookTags(bookCode, bookTags));
	}
}
