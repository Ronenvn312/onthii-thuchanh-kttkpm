package com.example.PassengerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableAutoConfiguration
@RequiredArgsConstructor
@EnableEurekaClient
public class PassengerServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(PassengerServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PassengerServiceApplication.class, args);
		LOG.info("Springboot redis application is started successfully.");
	}

}
