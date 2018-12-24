package com.nb.module.partner.weixin.client.api.token.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.partner.weixin.client.api.token.biz.IWeixinTokenService;
import com.nb.module.partner.weixin.client.api.token.client.ITokenClient;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.CgiBinWeixinUserInfo;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;
import com.nb.module.partner.weixin.client.exception.WeixinCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinTokenServiceImpl extends CommonServiceImpl implements IWeixinTokenService {

	@Autowired
	private ITokenClient client;

	@Override
	public AccessToken accessToken(String grantType, String appId, String appSecret) {
		AccessToken accessToken;
		String result = client.accessToken(grantType, appId, appSecret);
		try {
			accessToken = JSON.parseObject(result, AccessToken.class);
			if (StringUtils.isBlank(accessToken.getAccessToken())) {
				throw new BusinessException(WeixinCode.WX0001, null, result);
			}
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0001, e, result);
		}
		return accessToken;
	}

	@Override
	public JsapiTicket getTicket(String accessToken, String type) {
		JsapiTicket jsapiTicket;
		String result = client.getTicket(accessToken, type);
		try {
			jsapiTicket = JSON.parseObject(result, JsapiTicket.class);
			if (0 != jsapiTicket.getErrcode()) {
				throw new BusinessException(WeixinCode.WX0002, null, result);
			}
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0002, e, result);
		}
		return jsapiTicket;
	}

	@Override
	public CgiBinWeixinUserInfo getUserInfo(String accessToken, String openid, String lang) {
		CgiBinWeixinUserInfo cgiBinWeixinUserInfo;
		String result = client.getUserInfo(accessToken, openid, lang);
		try {
			cgiBinWeixinUserInfo = JSON.parseObject(result, CgiBinWeixinUserInfo.class);
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0006, e, result);
		}
		return cgiBinWeixinUserInfo;
	}

	@Override
	public boolean isFollow(String accessToken, String openid, String lang) {
		CgiBinWeixinUserInfo cgiBinWeixinUserInfo = getUserInfo(accessToken, openid, lang);
		if (1 == cgiBinWeixinUserInfo.getSubscribe()) {
			return true;
		}
		return false;
	}
}
