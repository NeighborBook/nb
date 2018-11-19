package com.nb.module.partner.weixin.lbs.client.api.suggestion.client;

import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;
import com.nb.module.partner.weixin.lbs.client.constant.WeixinLbsConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weixinlbssuggestion", url = WeixinLbsConstant.WEIXIN_LBS_URL)
@RequestMapping("/ws/place/v1")
public interface IWeixinLbsSuggestionClient {

	@RequestMapping(value = "/suggestion", method = RequestMethod.GET)
	SuggestResult suggestion(@RequestParam(value = "keyword") String keyword,
							 @RequestParam(value = "region") String region,
							 @RequestParam(value = "region_fix") String region_fix,
							 @RequestParam(value = "policy") String policy,
							 @RequestParam(value = "key") String key);

}
