package com.nb.module.nb.customer.api.taggroup.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.nb.customer.api.tag.biz.ITagService;
import com.nb.module.nb.customer.api.taggroup.biz.ITagGroupService;
import com.nb.module.nb.customer.api.taggroup.constant.TagGroupConstant;
import com.nb.module.nb.customer.api.taggroup.domain.TagGroup;
import com.nb.module.nb.customer.base.taggroup.biz.ITNBTagGroupService;
import com.nb.module.nb.customer.base.taggroup.domain.TNBTagGroup;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.redis.biz.IRedisService;
import com.zjk.module.common.redis.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagGroupServiceImpl extends CommonServiceImpl implements ITagGroupService {

	@Autowired
	private ITNBTagGroupService tagGroupService;

	@Autowired
	private ITagService tagService;
	@Autowired
	private IRedisService redisService;

	@Override
	@Transactional
	public void save(TagGroup tagGroup) {
		// 标签组
		TNBTagGroup po = tagGroupService.findOneByName(tagGroup.getName());
		if (null == po) {
			po = tagGroupService.newInstance();
			po.setName(tagGroup.getName());
			po.setTitle(tagGroup.getTitle());
		}
		// 没有visible则默认 0-不可见
		if (null == tagGroup.getVisible()) {
			tagGroup.setVisible(TagGroupConstant.INVISIBLE);
		}
		po.setVisible(tagGroup.getVisible());
		// 没有order则默认1
		if (null == tagGroup.getPosition() || 0 == tagGroup.getPosition()) {
			tagGroup.setPosition(1);
		}
		po.setPosition(tagGroup.getPosition());
		tagGroupService.save(po);
		// 标签组code
		tagGroup.setCode(po.getCode());

		// 标签
		tagService.saveTagGroupTags(po.getCode(), tagGroup.getTagGroupTags());
	}

	@Override
	public List<TagGroup> findAllByVisible(Integer visible) {
		List<TagGroup> tagGroups = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(TagGroup.class, visible, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			tagGroups = JSON.parseArray(valuer, TagGroup.class);
		}
		return tagGroups;
	}

	@Override
	@Transactional
	public void deleteByCode(String tagGroupCode) {
		tagGroupService.deleteByCode(tagGroupCode);
		tagService.deleteByTagGroupCode(tagGroupCode);
	}

	@Override
	@Scheduled(cron = "${sync.cron.api.taggroup}")
	public void refresh() {
		// 可见列表
		List<TagGroup> visibleTagGroups = map(tagGroupService.findAllByVisibleOrderByPosition(TagGroupConstant.VISIBLE), e -> {
			TagGroup tagGroup = new TagGroup(e.getCode(), e.getName(), e.getTitle(), e.getVisible(), e.getPosition());
			tagGroup.setTagGroupTags(tagService.findAllByTagGroupCodeOrderByOrder(e.getCode()));
			return tagGroup;
		});
		// 存入缓存
		redisService.set2RedisTwoDays(RedisConstant.getKey(TagGroup.class, TagGroupConstant.VISIBLE, RedisConstant.LIST), JSON.toJSONString(visibleTagGroups));

		// 不可见列表
		List<TagGroup> invisibleTagGroups = map(tagGroupService.findAllByVisibleOrderByPosition(TagGroupConstant.INVISIBLE), e -> {
			TagGroup tagGroup = new TagGroup(e.getCode(), e.getName(), e.getTitle(), e.getVisible(), e.getPosition());
			tagGroup.setTagGroupTags(tagService.findAllByTagGroupCodeOrderByOrder(e.getCode()));
			return tagGroup;
		});
		// 存入缓存
		redisService.set2RedisTwoDays(RedisConstant.getKey(TagGroup.class, TagGroupConstant.INVISIBLE, RedisConstant.LIST), JSON.toJSONString(invisibleTagGroups));
	}
}
