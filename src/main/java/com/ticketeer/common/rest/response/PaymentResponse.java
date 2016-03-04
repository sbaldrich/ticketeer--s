package com.ticketeer.common.rest.response;

import com.ticketeer.microservices.accounts.model.User;

public class PaymentResponse {

    private Status status;
    private User user;
    private String message;

    public PaymentResponse() {}

    public PaymentResponse(Status status, User user, String message) {
        this.status = status;
        this.user = user;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PaymentResponse idiot(){
        return new PaymentResponse(Status.YOURE_AN_IDIOT, null, "You're an idiot.");
    }

    public static PaymentResponse ok(User user){
        return new PaymentResponse(Status.OK, user, "Payment processed successfully.");
    }

    public static PaymentResponse from(DebitResponse response) {
        if(response.getStatus().getCode() == 0){
           return ok(response.getUser());
        }
        return idiot();
    }


    public static enum Status {

        EVENT_DOES_NOT_EXIST((short)-1), YOURE_AN_IDIOT((short)-2), OK((short)0);

        private int code;

        Status(int code){
            this.code = code;
        }

        int getCode(){
            return code;
        }
    }


}
