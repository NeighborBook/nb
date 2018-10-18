package com.nb.module.partner.douban.client.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nb.module.partner.douban.client"})
@EnableFeignClients(basePackages = {"com.nb.module.partner.douban.client"})
public class DoubanClientConfiguration {


}
