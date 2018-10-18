package com.nb.module.partner.weixin.client.api.token.client;

import com.nb.module.partner.weixin.client.constant.WeixinConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "token", url = WeixinConstant.WEIXIN_URL)
public interface ITokenClient {

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	String accessToken(@RequestParam(value = "grant_type") String grantType,
					   @RequestParam(value = "appid") String appId,
					   @RequestParam(value = "secret") String appSecret);

	@RequestMapping(value = "/ticket/getticket", method = RequestMethod.GET)
	String getTicket(@RequestParam(value = "access_token") String accessToken,
					 @RequestParam(value = "type") String type);

}
