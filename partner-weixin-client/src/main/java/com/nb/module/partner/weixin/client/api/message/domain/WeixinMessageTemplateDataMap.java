package com.nb.module.partner.weixin.client.api.message.domain;

import java.util.HashMap;

public class WeixinMessageTemplateDataMap extends HashMap<String, WeixinMessageTemplateData> {

	public static final String COLOR_173177 = "#173177";

	public WeixinMessageTemplateDataMap() {
	}

	public void put(String key, String message) {
		put(key, message, COLOR_173177);
	}

	public void put(String key, String message, String color) {
		put(key, new WeixinMessageTemplateData(message, color));
	}
}
