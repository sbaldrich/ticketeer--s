package com.ticketeer.microservices.tickets.service;

import com.ticketeer.common.rest.request.DebitRequest;
import com.ticketeer.common.rest.request.PaymentRequest;
import com.ticketeer.common.rest.response.DebitResponse;
import com.ticketeer.common.rest.response.PaymentResponse;
import com.ticketeer.microservices.accounts.model.User;
import com.ticketeer.microservices.tickets.model.Event;
import com.ticketeer.microservices.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Autowired @LoadBalanced
    private RestTemplate rest;

    @Autowired
    private EventRepository events;

    protected String serviceUrl;

    private static final String DEBIT_ENDPOINT = "/debit";

    public PaymentService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public User getUser(String id){
        User user = rest.getForObject(serviceUrl + "/user/{id}", User.class, id);
        return user;
    }

    public PaymentResponse carryOutPayment(PaymentRequest paymentRequest){
        Event event = events.findOne(paymentRequest.getEvent());
        if(event == null ){
            return PaymentResponse.idiot();
        }
        DebitRequest request = new DebitRequest();
        request.setUser(paymentRequest.getUser());
        request.setAmount(event.getPrice());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DebitRequest> entity = new HttpEntity<DebitRequest>(request, headers);
        DebitResponse response = rest.postForObject(serviceUrl + DEBIT_ENDPOINT, entity, DebitResponse.class);
        return PaymentResponse.from(response);
    }
};