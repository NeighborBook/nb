package com.nb.module.nb.customer.api.weixin.lbs.biz;


import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;

public interface ILbsService {

	SuggestResult suggestion(String keyword, String key);

}
