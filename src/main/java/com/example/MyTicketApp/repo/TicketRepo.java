package com.example.MyTicketApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyTicketApp.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
