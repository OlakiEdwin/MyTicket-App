package com.example.MyTicketApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyTicketApp.model.Ticket;
import com.example.MyTicketApp.repo.TicketRepo;

@Service
public class TicketService {

    @Autowired
    private TicketRepo repo;

    public List<Ticket> getAllTicketItems() {
        List<Ticket> ticketList = new ArrayList<>();
        repo.findAll().forEach(ticketList::add);

        return ticketList;
    }

    public Ticket getTicketItemById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean updateStatus(Long id) {
        Ticket ticket = getTicketItemById(id);
        if (ticket != null) {
            ticket.setStatus("Completed");
            return saveOrUpdateTicketItem(ticket);
        }
        return false;
    }

    public boolean saveOrUpdateTicketItem(Ticket ticket) {
        Ticket updatedTicket = repo.save(ticket);
        return updatedTicket != null;
    }

    public boolean deleteTicketItem(Long id) {
        repo.deleteById(id);
        return !repo.existsById(id);
    }
}
