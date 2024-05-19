package com.thanhtd.spring_boot_integration_tcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootIntegrationTcpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationTcpClientApplication.class, args);
	}

}
