package com.ticketeer.microservices.accounts.controller;

import com.ticketeer.common.rest.request.DebitRequest;
import com.ticketeer.common.rest.response.DebitResponse;
import com.ticketeer.microservices.accounts.model.User;
import com.ticketeer.microservices.accounts.repository.UserRepository;
import com.ticketeer.microservices.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.MediaType;

@Controller
public class RestController{

    @Autowired
    private UserRepository users;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/user/{id}")
    public @ResponseBody
    User getUser(@PathVariable String id){
        return users.findOne(Long.parseLong(id));
    }

    @RequestMapping(value = "/debit", consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ResponseEntity debit(@RequestBody DebitRequest request){
        User user = accountService.debit(request.getUser(), request.getAmount());
        return user != null ? ResponseEntity.ok(DebitResponse.ok(user)) : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(DebitResponse.idiot());
    }

}