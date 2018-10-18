package com.nb.module;

import com.nb.module.partner.aliyun.annotation.EnableAliyunConfiguration;
import com.nb.module.partner.douban.client.annotation.EnableDoubanClientConfiguration;
import com.nb.module.partner.szmesoft.client.annotation.EnableSzmesoftClientConfiguration;
import com.nb.module.partner.weixin.client.annotation.EnableWeixinClientConfiguration;
import com.zjk.module.common.authorization.client.annotation.EnableCommonAuthorizationClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCommonAuthorizationClientConfiguration
@EnableAliyunConfiguration
@EnableDoubanClientConfiguration
@EnableSzmesoftClientConfiguration
@EnableWeixinClientConfiguration
public class NbCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbCustomerApplication.class, args);
	}
}
