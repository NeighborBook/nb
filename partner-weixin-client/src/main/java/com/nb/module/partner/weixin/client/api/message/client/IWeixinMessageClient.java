package com.nb.module.partner.weixin.client.api.message.client;

import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.constant.WeixinConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "message", url = WeixinConstant.WEIXIN_URL)
@RequestMapping("/cgi-bin")
public interface IWeixinMessageClient {

	@RequestMapping(value = "/template/send", method = RequestMethod.POST)
	String templateSend(@RequestParam(value = "access_token") String accessToken, @RequestBody WeixinMessageTemplate template);

}
