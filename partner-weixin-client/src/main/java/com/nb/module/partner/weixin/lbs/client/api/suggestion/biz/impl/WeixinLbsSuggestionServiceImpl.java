package com.nb.module.partner.weixin.lbs.client.api.suggestion.biz.impl;


import com.nb.module.partner.weixin.lbs.client.api.suggestion.biz.IWeixinLbsSuggestionService;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.client.IWeixinLbsSuggestionClient;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;
import com.nb.module.partner.weixin.lbs.client.exception.WeixinLbsCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinLbsSuggestionServiceImpl extends CommonServiceImpl implements IWeixinLbsSuggestionService {

	@Autowired
	private IWeixinLbsSuggestionClient client;

	@Override
	public SuggestResult suggestion(String keyword, String region, String key, String secretKey) {
		SuggestResult result = client.suggestion(keyword, region, "1", "1", key);
		if (0 != result.getStatus()) {
			throw new BusinessException(WeixinLbsCode.LBS0001, null, result);
		}
		return result;
	}

}
