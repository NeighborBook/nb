package com.nb.module.partner.weixin.client.api.message.biz;


import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateResult;

public interface IWeixinMessageService {

	WeixinMessageTemplateResult templateSend(String accessToken, WeixinMessageTemplate template);
}
