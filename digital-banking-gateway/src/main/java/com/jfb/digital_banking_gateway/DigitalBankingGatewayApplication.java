package com.jfb.digital_banking_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DigitalBankingGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingGatewayApplication.class, args);
	}

}
