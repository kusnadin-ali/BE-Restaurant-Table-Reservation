package com.tujuhsembilan.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.tujuhsembilan.core.config.SecurityConfig;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.tujuhsembilan.user_service", "com.tujuhsembilan.core"})
@Import(SecurityConfig.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
