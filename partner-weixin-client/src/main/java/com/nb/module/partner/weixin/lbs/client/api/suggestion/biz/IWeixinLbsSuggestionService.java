package com.nb.module.partner.weixin.lbs.client.api.suggestion.biz;

import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;

public interface IWeixinLbsSuggestionService {

	SuggestResult suggestion(String keyword, String region, String key, String secretKey);

}
