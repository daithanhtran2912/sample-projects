package com.thanhtd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootIntegrationTcpIpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationTcpIpClientApplication.class, args);
	}

}
