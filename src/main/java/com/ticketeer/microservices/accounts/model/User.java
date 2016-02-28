package com.ticketeer.microservices.accounts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int money;

    public User() {}

    public User(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean debit(int amount){
        if(money - amount < 0) {
            return false;
        }
        money -= amount;
        return true;
    }
    @Override
    public String toString(){
        return String.format("User{ %d, %d }", id, money);
    }
}
