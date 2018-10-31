package com.nb.module.nb.customer.api.tag.biz.impl;

import com.nb.module.nb.customer.api.tag.biz.ITagService;
import com.nb.module.nb.customer.api.tag.domain.*;
import com.nb.module.nb.customer.base.booktag.biz.ITNBBookTagService;
import com.nb.module.nb.customer.base.booktag.domain.TNBBookTag;
import com.nb.module.nb.customer.base.tag.biz.ITNBTagService;
import com.nb.module.nb.customer.base.tag.domain.TNBTag;
import com.nb.module.nb.customer.base.taggrouptag.biz.ITNBTagGroupTagService;
import com.nb.module.nb.customer.base.taggrouptag.domain.TNBTagGroupTag;
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
	@Autowired
	private ITNBTagGroupTagService tagGroupTagService;

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
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TagGroupTag> findAllByTagGroupCodeOrderByOrder(String tagGroupCode) {
		return map(tagGroupTagService.findAllByTagGroupCodeOrderByPosition(tagGroupCode), s -> new TagGroupTag(s.getPosition(), findOneByCode(s.getTagCode())));
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
			// 名称不能为空
			if (StringUtils.isNotBlank(tag.getName())) {
				save(tag);
				// 书标签关联
				TNBBookTag po = bookTagService.findOneByBookCodeAndTagCode(bookTags.getBookCode(), tag.getCode());
				if (null == po) {
					po = new TNBBookTag();
					po.setBookCode(bookTags.getBookCode());
					po.setTagCode(tag.getCode());
					// 没有tagCount则默认1
					if (null == e.getTagCount() || 0 == e.getTagCount()) {
						e.setTagCount(1);
					}
					po.setTagCount(e.getTagCount());
				} else {
					po.setTagCount(po.getTagCount() + 1);
				}
				bookTagService.save(po);
			}
		});
	}

	@Override
	@Transactional
	public void saveBookTags(String bookCode, List<BookTag> bookTags) {
		saveBookTags(new BookTags(bookCode, bookTags));
	}

	@Override
	@Transactional
	public void saveTagGroupTags(TagGroupTags tagGroupTags) {
		tagGroupTags.getTagGroupTags().forEach(e -> {
			// 保存Tag
			Tag tag = e.getTag();
			// 名称不能为空
			if (StringUtils.isNotBlank(tag.getName())) {
				save(tag);
				// 标签组标签关联
				TNBTagGroupTag po = tagGroupTagService.findOneByTagGroupCodeAndTagCode(tagGroupTags.getTagGroupCode(), tag.getCode());
				if (null == po) {
					po = new TNBTagGroupTag();
					po.setTagGroupCode(tagGroupTags.getTagGroupCode());
					po.setTagCode(tag.getCode());
					// 没有order则默认1
					if (null == e.getPosition() || 0 == e.getPosition()) {
						e.setPosition(1);
					}
					po.setPosition(e.getPosition());
				} else {
					// 存在order则修改
					if (null != e.getPosition() && 0 != e.getPosition()) {
						po.setPosition(e.getPosition());
					}
				}
				tagGroupTagService.save(po);
			}
		});
	}

	@Override
	@Transactional
	public void saveTagGroupTags(String tagCode, List<TagGroupTag> tagGroupTags) {
		saveTagGroupTags(new TagGroupTags(tagCode, tagGroupTags));
	}

	@Override
	@Transactional
	public void deleteByTagGroupCode(String tagGroupCode) {
		tagGroupTagService.deleteByTagGroupCode(tagGroupCode);
	}
}
