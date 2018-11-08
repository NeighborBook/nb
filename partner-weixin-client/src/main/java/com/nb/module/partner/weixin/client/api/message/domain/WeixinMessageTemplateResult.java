package com.nb.module.partner.weixin.client.api.message.domain;

import lombok.Data;

@Data
public class WeixinMessageTemplateResult {

	private Integer errcode;

	private String errmsg;

	private String msgid;
}
