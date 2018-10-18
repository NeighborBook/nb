package com.nb.module.partner.weixin.client.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nb.module.partner.weixin.client"})
@EnableFeignClients(basePackages = {"com.nb.module.partner.weixin.client"})
public class WeixinClientConfiguration {

}
