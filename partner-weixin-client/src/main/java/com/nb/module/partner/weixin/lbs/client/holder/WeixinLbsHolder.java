package com.nb.module.partner.weixin.lbs.client.holder;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class WeixinLbsHolder {

	@Value("${weixin.lbs.key}")
	private String key;

	@Value("${weixin.lbs.secretkey}")
	private String secretkey;

}
