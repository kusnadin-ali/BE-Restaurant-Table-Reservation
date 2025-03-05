package com.tujuhsembilan.table_management;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.tujuhsembilan.table_management", "com.tujuhsembilan.core"})
@Import(SecurityConfig.class)
public class TableManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableManagementApplication.class, args);
	}

}
