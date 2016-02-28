package com.ticketeer.microservices.accounts.service;


import com.ticketeer.microservices.accounts.model.User;
import com.ticketeer.microservices.accounts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository users;

    public User debit(Long id, int amount){
        User user = users.findOne(id);
        if(user != null && user.debit(amount)){
            users.save(user);
        }
        return user;
    }
}
