package com.nb.module;

import com.zjk.module.common.authorization.server.annotation.EnableCommonAuthorizationServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCommonAuthorizationServerConfiguration
public class NbAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbAuthorizationApplication.class, args);
	}
}
