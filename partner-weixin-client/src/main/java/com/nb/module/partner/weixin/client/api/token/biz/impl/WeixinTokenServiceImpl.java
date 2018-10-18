package com.nb.module.partner.weixin.client.api.token.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.partner.weixin.client.api.token.biz.IWeixinTokenService;
import com.nb.module.partner.weixin.client.api.token.client.ITokenClient;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;
import com.nb.module.partner.weixin.client.exception.WeixinCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
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
}
