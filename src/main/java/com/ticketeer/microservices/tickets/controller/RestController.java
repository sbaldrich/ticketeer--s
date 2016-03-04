package com.ticketeer.microservices.tickets.controller;

import com.ticketeer.common.rest.request.PaymentRequest;
import com.ticketeer.common.rest.response.PaymentResponse;
import com.ticketeer.microservices.tickets.repository.EventRepository;
import com.ticketeer.microservices.tickets.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.MediaType;

@Controller
public class RestController {

    @Autowired private PaymentService paymentService;

    @Autowired private EventRepository events;

    @RequestMapping(path="purchase", consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity pay(@RequestBody PaymentRequest paymentRequest){
        PaymentResponse response = paymentService.carryOutPayment(paymentRequest);
        return response.getStatus().equals(PaymentResponse.Status.OK) ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(response);
    }

    @RequestMapping(path="/", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity events(){
        return ResponseEntity.ok(events.findAll());
    }

}
