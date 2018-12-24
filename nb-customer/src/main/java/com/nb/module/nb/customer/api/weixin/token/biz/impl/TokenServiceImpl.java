package com.nb.module.nb.customer.api.weixin.token.biz.impl;

import com.nb.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.nb.module.partner.weixin.client.api.sns.constant.SnsConstant;
import com.nb.module.partner.weixin.client.api.token.biz.IWeixinTokenService;
import com.nb.module.partner.weixin.client.api.token.constant.TokenConstant;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;
import com.nb.module.partner.weixin.client.constant.WeixinConstant;
import com.nb.module.partner.weixin.client.holder.WeixinHolder;
import com.nb.module.partner.weixin.client.util.Sign;
import com.nb.module.partner.weixin.client.util.Signature;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.redis.biz.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl extends CommonServiceImpl implements ITokenService {

	@Autowired
	private WeixinHolder holder;
	@Autowired
	private IWeixinTokenService weixinTokenService;
	@Autowired
	private IRedisService redisService;

	@Override
	public AccessToken accessToken(boolean refresh) {
		AccessToken accessToken = (AccessToken) redisService.get(TokenConstant.WEIXIN_ACCESS_TOKEN);
		if (null == accessToken || refresh) {
			accessToken = weixinTokenService.accessToken(TokenConstant.GRAND_TYPE, holder.getAppId(), holder.getAppSecret());
			// 放入缓存
			redisService.set2Redis(TokenConstant.WEIXIN_ACCESS_TOKEN, accessToken, WeixinConstant.WEIXIN_EXPIRES_IN, TimeUnit.SECONDS);
		}
		return accessToken;
	}

	@Override
	public JsapiTicket getTicket() {
		JsapiTicket jsapiTicket = (JsapiTicket) redisService.get(TokenConstant.WEIXIN_JSAPI_TICKET);
		if (null == jsapiTicket) {
			jsapiTicket = weixinTokenService.getTicket(accessToken(false).getAccessToken(), TokenConstant.JS_API);
			// 放入缓存
			redisService.set2Redis(TokenConstant.WEIXIN_JSAPI_TICKET, jsapiTicket, WeixinConstant.WEIXIN_EXPIRES_IN, TimeUnit.SECONDS);
		}
		return jsapiTicket;
	}

	@Override
	public Sign sign(String url) {
		JsapiTicket jsapiTicket = getTicket();
		return Signature.sign(holder.getAppId(), jsapiTicket.getTicket(), url);
	}

	@Override
	public Boolean isFollow(String openid) {
		return weixinTokenService.isFollow(accessToken(false).getAccessToken(), openid, SnsConstant.LANG);
	}
}
