package com.nb.module.partner.weixin.client.api.sns.client;

import com.nb.module.partner.weixin.client.constant.WeixinConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sns", url = WeixinConstant.WEIXIN_URL)
@RequestMapping("/sns")
public interface ISnsClient {

	@RequestMapping(value = "/access_token", method = RequestMethod.GET)
	String accessToken(@RequestParam(value = "appid") String appId,
					   @RequestParam(value = "secret") String appSecret,
					   @RequestParam(value = "code") String code,
					   @RequestParam(value = "grant_type") String grantType);

	@RequestMapping(value = "/ticket/userinfo", method = RequestMethod.GET)
	String getUserInfo(@RequestParam(value = "access_token") String accessToken,
					   @RequestParam(value = "openid") String openid,
					   @RequestParam(value = "lang") String lang);

}
