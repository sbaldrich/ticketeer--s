package com.ticketeer.microservices.tickets.repository;


import com.ticketeer.microservices.tickets.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EventRepository extends PagingAndSortingRepository<Event, Long>{
}
