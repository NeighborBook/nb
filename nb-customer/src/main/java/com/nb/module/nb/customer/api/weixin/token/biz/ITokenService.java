package com.nb.module.nb.customer.api.weixin.token.biz;


import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.token.domain.JsapiTicket;
import com.nb.module.partner.weixin.client.util.Sign;

public interface ITokenService {

	AccessToken accessToken();

	JsapiTicket getTicket();

	Sign sign(String url);

}
