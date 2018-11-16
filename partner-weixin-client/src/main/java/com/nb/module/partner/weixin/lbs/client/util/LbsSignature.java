package com.nb.module.partner.weixin.lbs.client.util;


import com.zjk.module.common.base.util.CodecUtil;
import lombok.SneakyThrows;

import java.net.URLEncoder;

public class LbsSignature {

	@SneakyThrows
	public static String sign(String url, String secretKey) {
		String baseString = url + secretKey;
		String sn = CodecUtil.md5Hex(URLEncoder.encode(baseString, "utf-8"));
		return sn;
	}

}
