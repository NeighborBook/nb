package com.nb.module.partner.bookschina.client.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nb.module.partner.bookschina.client"})
@EnableFeignClients(basePackages = {"com.nb.module.partner.bookschina.client"})
public class BookschinaClientConfiguration {


}
