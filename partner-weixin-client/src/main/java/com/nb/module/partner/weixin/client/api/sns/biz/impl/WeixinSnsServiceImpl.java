package com.nb.module.partner.weixin.client.api.sns.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.partner.weixin.client.api.sns.biz.IWeixinSnsService;
import com.nb.module.partner.weixin.client.api.sns.client.ISnsClient;
import com.nb.module.partner.weixin.client.api.sns.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.sns.domain.WeixinUserInfo;
import com.nb.module.partner.weixin.client.exception.WeixinCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinSnsServiceImpl extends CommonServiceImpl implements IWeixinSnsService {

	@Autowired
	private ISnsClient client;

	@Override
	public AccessToken accessToken(String appId, String appSecret, String grantType) {
		AccessToken accessToken;
		String result = client.accessToken(appId, appSecret, grantType);
		try {
			accessToken = JSON.parseObject(result, AccessToken.class);
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0003, e, result);
		}
		return accessToken;
	}

	@Override
	public WeixinUserInfo getUserInfo(String accessToken, String openid, String lang) {
		WeixinUserInfo weixinUserInfo;
		String result = client.getUserInfo(accessToken, openid, lang);
		try {
			weixinUserInfo = JSON.parseObject(result, WeixinUserInfo.class);
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0004, e, result);
		}
		return weixinUserInfo;
	}
}
