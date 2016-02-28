package com.ticketeer.microservices.tickets.controller;

import com.ticketeer.common.request.Payment;
import com.ticketeer.microservices.accounts.model.User;
import com.ticketeer.microservices.tickets.service.AccountsServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.MediaType;

@Controller
public class TicketController {

    @Autowired
    private AccountsServiceClient accountsServiceClient;

    @RequestMapping(path="pay", consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity pay(@RequestBody Payment payment){
        User user = accountsServiceClient.carryOutPayment(payment.getUser(), payment.getAmount());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }

}
