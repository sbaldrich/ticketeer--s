package com.ticketeer.microservices.accounts.repository;


import com.ticketeer.microservices.accounts.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
