package com.nb.module.partner.weixin.client.api.token.biz;


import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;

public interface IWeixinTokenService {

	AccessToken accessToken(String grantType, String appId, String appSecret);

	JsapiTicket getTicket(String accessToken, String type);

}
