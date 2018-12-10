package com.nb.module.partner.aliyun.configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.nb.module.partner.aliyun.dysmsapi.constant.DySmsApiConstant;
import com.nb.module.partner.aliyun.holder.AliyunDySmsApiHolder;
import com.nb.module.partner.aliyun.holder.AliyunHolder;
import lombok.SneakyThrows;
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

	@Value("${aliyun.dysmsapi.accessKeyId}")
	private String dysmsapiAccessKeyId;

	@Value("${aliyun.dysmsapi.accessKeySecret}")
	private String dysmsapiAccessKeySecret;

	@Bean
	public AliyunDySmsApiHolder getAliyunDySmsApiHolder() throws Exception {
		return new AliyunDySmsApiHolder(dysmsapiAccessKeyId, dysmsapiAccessKeySecret);
	}

	@Bean
	@SneakyThrows
	public IAcsClient getAcsClient() {
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile(DySmsApiConstant.REGION_ID, accessKeyId,
				accessKeySecret);
		DefaultProfile.addEndpoint(DySmsApiConstant.END_POINT_NAME, DySmsApiConstant.REGION_ID, DySmsApiConstant.PRODUCT, DySmsApiConstant.DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		return acsClient;
	}

}
