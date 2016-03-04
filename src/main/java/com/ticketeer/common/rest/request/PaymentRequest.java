package com.ticketeer.common.rest.request;

public class PaymentRequest {
    private Long user;

    private Long event;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }
}