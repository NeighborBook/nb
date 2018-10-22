package com.nb.module.partner.weixin.client.api.sns.biz;


import com.nb.module.partner.weixin.client.api.sns.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.sns.domain.WeixinUserInfo;

public interface IWeixinSnsService {

	AccessToken accessToken(String appId, String appSecret, String grantType);

	WeixinUserInfo getUserInfo(String accessToken, String openid, String lang);

}
