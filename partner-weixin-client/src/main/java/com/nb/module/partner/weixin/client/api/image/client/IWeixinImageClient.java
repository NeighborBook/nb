package com.nb.module.partner.weixin.client.api.image.client;

import com.nb.module.partner.weixin.client.constant.WeixinConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "weixinimage", url = WeixinConstant.WEIXIN_IMAGE_URL)
@RequestMapping("/mmopen")
public interface IWeixinImageClient {

	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	ResponseEntity<byte[]> image(@PathVariable(value = "path") String path);

}
