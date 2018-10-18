package com.nb.module.partner.aliyun.holder;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;

@Data
public class AliyunHolder {

	private String accessKeyId;

	private String accessKeySecret;

	private String endpoint;

	private String bucketName;

	private OSS client;

	public AliyunHolder(String accessKeyId, String accessKeySecret, String endpoint, String bucketName) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.endpoint = endpoint;
		this.bucketName = bucketName;
	}

	public AliyunHolder build() {
		this.client = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);
		return this;
	}

}
