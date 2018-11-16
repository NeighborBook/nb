package com.nb.module.partner.weixin.lbs.client.api.suggestion.biz.impl;


import com.nb.module.partner.weixin.lbs.client.api.suggestion.biz.IWeixinLbsSuggestionService;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.client.IWeixinLbsSuggestionClient;
import com.nb.module.partner.weixin.lbs.client.api.suggestion.domain.SuggestResult;
import com.nb.module.partner.weixin.lbs.client.exception.WeixinLbsCode;
import com.nb.module.partner.weixin.lbs.client.util.LbsSignature;
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
		StringBuffer sb = new StringBuffer("/ws/place/v1/suggestion/?");
		sb.append("keyword=").append(keyword).append("&");
		sb.append("region=").append(region).append("&");
		sb.append("region_fix=").append(1).append("&");
		sb.append("policy=").append(1).append("&");
		sb.append("key=").append(key);
		String sn = LbsSignature.sign(sb.toString(), secretKey);
		System.out.println(sn);
		SuggestResult result = client.suggestion(keyword, region, "1", "1", key, sn);
		if (0 != result.getStatus()) {
			throw new BusinessException(WeixinLbsCode.LBS0001, null, result);
		}
		return result;
	}

}
