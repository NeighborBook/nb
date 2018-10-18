package com.nb.module.nb.customer.api.weixin.token.biz;


import com.sbm.module.partner.weixin.rest.token.domain.AccessToken;
import com.sbm.module.partner.weixin.rest.token.domain.JsapiTicket;
import com.sbm.module.partner.weixin.util.Sign;

public interface ITokenService {

	AccessToken accessToken();

	JsapiTicket getTicket();

	Sign sign(String url);

}
