package com.nb.module.partner.weixin.client.api.message.domain;

import lombok.Data;

import java.util.Map;

@Data
public class WeixinMessageTemplate {

	private String touser;

	private String template_id;

	private String url;

	private Map<String, Map<String, String>> data;

}
