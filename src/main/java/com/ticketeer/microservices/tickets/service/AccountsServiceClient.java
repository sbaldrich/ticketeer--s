package com.ticketeer.microservices.tickets.service;

import com.ticketeer.common.request.DebitRequest;
import com.ticketeer.microservices.accounts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountsServiceClient {
    @Autowired @LoadBalanced
    private RestTemplate rest;

    protected String serviceUrl;

    public AccountsServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public User getUser(String id){
        User user = rest.getForObject(serviceUrl + "/user/{id}", User.class, id);
        return user;
    }

    public User carryOutPayment(Long userId, int amount){
        DebitRequest request = new DebitRequest();
        request.setUser(userId);
        request.setAmount(amount);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DebitRequest> entity = new HttpEntity<DebitRequest>(request, headers);
        User response = rest.postForObject(serviceUrl + "/debit", entity, User.class);
        return response;
    }
};