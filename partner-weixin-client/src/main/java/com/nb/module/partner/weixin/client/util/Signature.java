package com.nb.module.partner.weixin.client.util;


import com.zjk.module.common.base.util.CodecUtil;

import java.util.UUID;

public class Signature {

	public static Sign sign(String appId, String ticket, String url) {
		String nonceStr = createNonceStr();
		String timestamp = createTimestamp();
		StringBuffer sb = new StringBuffer("jsapi_ticket=").append(ticket).append("&noncestr=").append(nonceStr)
				.append("&timestamp=").append(timestamp).append("&url=").append(url);
		String signature = CodecUtil.sha1Hex(sb.toString());
		return new Sign(appId, nonceStr, timestamp, signature);
	}

	public static String createNonceStr() {
		return UUID.randomUUID().toString();
	}

	public static String createTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

}
