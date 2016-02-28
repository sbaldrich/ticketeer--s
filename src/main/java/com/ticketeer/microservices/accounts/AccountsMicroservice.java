package com.ticketeer.microservices.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AccountsApplication.class)
public class AccountsMicroservice {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "accounts-service");
        SpringApplication.run(AccountsMicroservice.class, args);
    }
}
