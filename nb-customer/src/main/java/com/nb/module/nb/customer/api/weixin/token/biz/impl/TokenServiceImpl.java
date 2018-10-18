package com.nb.module.nb.customer.api.weixin.token.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.sbm.module.partner.weixin.constant.WeixinConstant;
import com.sbm.module.partner.weixin.holder.WeixinHolder;
import com.sbm.module.partner.weixin.rest.token.biz.IWeixinTokenService;
import com.sbm.module.partner.weixin.rest.token.constant.TokenConstant;
import com.sbm.module.partner.weixin.rest.token.domain.AccessToken;
import com.sbm.module.partner.weixin.rest.token.domain.JsapiTicket;
import com.sbm.module.partner.weixin.util.Sign;
import com.sbm.module.partner.weixin.util.Signature;
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
	public AccessToken accessToken() {
		AccessToken accessToken = (AccessToken) redisService.get(WeixinConstant.WEIXIN_ACCESS_TOKEN);
		if (null == accessToken) {
			accessToken = weixinTokenService.accessToken(TokenConstant.GRAND_TYPE, holder.getAppId(), holder.getAppSecret());
			// 放入缓存
			redisService.set2Redis(WeixinConstant.WEIXIN_ACCESS_TOKEN, accessToken, WeixinConstant.WEIXIN_EXPIRES_IN, TimeUnit.SECONDS);
		}
		return accessToken;
	}

	@Override
	public JsapiTicket getTicket() {
		JsapiTicket jsapiTicket = (JsapiTicket) redisService.get(WeixinConstant.WEIXIN_JSAPI_TICKET);
		if (null == jsapiTicket) {
			jsapiTicket = weixinTokenService.getTicket(accessToken().getAccessToken(), TokenConstant.JS_API);
			// 放入缓存
			redisService.set2Redis(WeixinConstant.WEIXIN_JSAPI_TICKET, jsapiTicket, WeixinConstant.WEIXIN_EXPIRES_IN, TimeUnit.SECONDS);
		}
		return jsapiTicket;
	}

	@Override
	public Sign sign(String url) {
		JsapiTicket jsapiTicket = getTicket();
		return Signature.sign(holder.getAppId(), jsapiTicket.getTicket(), url);
	}
}
