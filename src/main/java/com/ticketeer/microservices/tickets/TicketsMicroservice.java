package com.ticketeer.microservices.tickets;

import com.ticketeer.microservices.tickets.model.Event;
import com.ticketeer.microservices.tickets.repository.EventRepository;
import com.ticketeer.microservices.tickets.service.AccountsServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketsMicroservice implements CommandLineRunner{

    @Autowired
    private AccountsServiceClient accounts;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "tickets-service");
        SpringApplication.run(TicketsMicroservice.class, args);
    }

    @Bean
    @Value("${microservice.accounts.location}")
    public AccountsServiceClient accountsServiceClient(String location){
        return new AccountsServiceClient(location);
    }


    @Autowired
    private EventRepository events;

    @Override
    public void run(String... args) throws Exception {
        String[] names = {"Mentallica", "Food Fighters", "Lead Zappelin"};
        Random rand = new Random();
        for(String name : names){
            events.save(new Event(null, rand.nextInt(2000), name));
        }
    }
}
