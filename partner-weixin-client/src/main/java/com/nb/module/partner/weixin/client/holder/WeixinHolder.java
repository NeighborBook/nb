package com.nb.module.partner.weixin.client.holder;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class WeixinHolder {

	@Value("${weixin.appId}")
	private String appId;

	@Value("${weixin.appSecret}")
	private String appSecret;

}
