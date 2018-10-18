package com.nb.module.partner.weixin.client.util;

import lombok.Data;

@Data
public class Sign {

	private String appId;

	private String nonceStr;

	private String timestamp;

	private String signature;

	public Sign(String appId, String nonceStr, String timestamp, String signature) {
		this.appId = appId;
		this.nonceStr = nonceStr;
		this.timestamp = timestamp;
		this.signature = signature;
	}

	public Sign() {

	}
}
