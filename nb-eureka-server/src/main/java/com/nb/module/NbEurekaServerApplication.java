package com.nb.module;

import com.zjk.module.common.base.annotation.EnableCommonBaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableCommonBaseConfiguration
public class NbEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbEurekaServerApplication.class, args);
	}
}
