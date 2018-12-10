package com.nb.module.partner.aliyun.holder;

import lombok.Data;

@Data
public class AliyunDySmsApiHolder {

	private String accessKeyId;

	private String accessKeySecret;

	public AliyunDySmsApiHolder(String accessKeyId, String accessKeySecret) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}
}
