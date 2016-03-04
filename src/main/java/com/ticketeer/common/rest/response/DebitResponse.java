package com.ticketeer.common.rest.response;

import com.ticketeer.microservices.accounts.model.User;

public class DebitResponse {
    private Status status;
    private User user;
    private String message;

    public DebitResponse() {}

    public DebitResponse(Status status, User user, String message) {
        this.status = status;
        this.user = user;
        this.message = message;
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

    public static DebitResponse ok(User user){
        return new DebitResponse(Status.OK, user, "Payment processed successfully.");
    }

    public static DebitResponse idiot(){
        return new DebitResponse(Status.YOURE_A_DUMBASS, null, "You're a dumbass.");
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


    public static enum Status {

        USER_DOES_NOT_EXIST((short)-2), YOURE_A_DUMBASS((short)-1), OK((short)0);

        private short code;

        Status(short code){
            this.code = code;
        }

        short getCode(){
            return code;
        }
    }
}
