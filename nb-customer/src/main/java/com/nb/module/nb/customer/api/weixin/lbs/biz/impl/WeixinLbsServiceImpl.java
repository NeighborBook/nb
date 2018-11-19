package com.nb.module.nb.customer.api.weixin.lbs.biz.impl;

import com.nb.module.nb.customer.api.weixin.lbs.biz.WeixinILbsService;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.biz.IWeixinLbsSuggestionService;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;
import com.nb.module.partner.weixin.lbs.client.holder.WeixinLbsHolder;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinLbsServiceImpl extends CommonServiceImpl implements WeixinILbsService {

	@Autowired
	private WeixinLbsHolder holder;
	@Autowired
	private IWeixinLbsSuggestionService weixinLbsSuggestionService;

	@Override
	public SuggestResult suggestion(String keyword, String region) {
		return weixinLbsSuggestionService.suggestion(keyword, region, holder.getKey(), holder.getSecretkey());
	}
}
