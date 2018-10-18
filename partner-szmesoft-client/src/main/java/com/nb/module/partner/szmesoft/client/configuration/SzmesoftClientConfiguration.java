package com.nb.module.partner.szmesoft.client.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nb.module.partner.szmesoft.client"})
@EnableFeignClients(basePackages = {"com.nb.module.partner.szmesoft.client"})
public class SzmesoftClientConfiguration {


}
