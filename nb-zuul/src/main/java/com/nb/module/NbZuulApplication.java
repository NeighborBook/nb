package com.nb.module;

import com.zjk.module.common.zuul.annotation.EnableCommonZuulConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCommonZuulConfiguration
public class NbZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbZuulApplication.class, args);
	}
}
