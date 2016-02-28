package com.ticketeer.microservices.accounts;

import com.ticketeer.microservices.accounts.model.User;
import com.ticketeer.microservices.accounts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ticketeer.services.accounts")
@EnableJpaRepositories("com.ticketeer.services.accounts")
public class AccountsApplication implements CommandLineRunner{
    public static void main(String[] args){
        SpringApplication.run(AccountsApplication.class, args);
    }

    @Autowired
    private UserRepository users;

    @Override
    public void run(String... args) throws Exception {
        users.save(new User(1000));
    }
}
