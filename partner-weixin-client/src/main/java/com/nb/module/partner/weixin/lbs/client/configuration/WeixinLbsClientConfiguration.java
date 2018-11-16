package com.nb.module.partner.weixin.lbs.client.configuration;

import com.zjk.module.common.base.annotation.EnableRegisterRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nb.module.partner.weixin.lbs.client"})
@EnableFeignClients(basePackages = {"com.nb.module.partner.weixin.lbs.client"})
@EnableRegisterRunner(basePackages = {"com.nb.module.partner.weixin.lbs.client"})
public class WeixinLbsClientConfiguration {

}
