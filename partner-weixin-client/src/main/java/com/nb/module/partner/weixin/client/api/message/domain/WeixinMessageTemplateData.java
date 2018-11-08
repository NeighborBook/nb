package com.nb.module.partner.weixin.client.api.message.domain;

import lombok.Data;

@Data
public class WeixinMessageTemplateData {

	private String value;

	private String color;

	public WeixinMessageTemplateData(String value, String color) {
		this.value = value;
		this.color = color;
	}

	public WeixinMessageTemplateData() {

	}
}
