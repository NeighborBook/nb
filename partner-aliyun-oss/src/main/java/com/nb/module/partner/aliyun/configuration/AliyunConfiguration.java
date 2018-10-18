package com.nb.module.partner.aliyun.configuration;

import com.nb.module.partner.aliyun.holder.AliyunHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
		basePackages = {"com.nb.module.partner.aliyun"}
)
public class AliyunConfiguration {

	@Value("${aliyun.accessKeyId}")
	private String accessKeyId;

	@Value("${aliyun.accessKeySecret}")
	private String accessKeySecret;

	@Value("${aliyun.endpoint}")
	private String endpoint;

	@Value("${aliyun.bucketName}")
	private String bucketName;

	@Bean
	public AliyunHolder getAliyunHolder() throws Exception {
		return new AliyunHolder(accessKeyId, accessKeySecret, endpoint, bucketName).build();
	}

}
