package com.example.MyTicketApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.MyTicketApp.model.Ticket;
import com.example.MyTicketApp.service.TicketService;

@Controller
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping({"/", "viewTicketList"})
    public String viewAllTicketItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getAllTicketItems());
        model.addAttribute("message", message);
        return "ViewTicketList";
    }

    @GetMapping("/updateTicketStatus/{id}")
    public String updateTicketStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewTicketList";
        }

        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewTicketList";
    }

    @GetMapping("/addTicketItem")
    public String addTicketItem(Model model) {
        model.addAttribute("ticket", new Ticket());

        return "AddTicketItem";
    }

    @PostMapping("/saveTicketItem")
    public String saveTicketItem(Ticket ticket, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateTicketItem(ticket)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewTicketList";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addTicketItem";
    }

    @GetMapping("/editTicketItem/{id}")
    public String editTicketItem(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", service.getTicketItemById(id));

        return "EditTicketItem";
    }

    @PostMapping("editSaveTicketItem")
    public String editSaveTicketItem(Ticket ticket, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateTicketItem(ticket)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewTicketList";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editTicketItem/" + ticket.getId();
    }

    @GetMapping("/deleteTicketItem/{id}")
    public String deleteTicketItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteTicketItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewTicketList";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewTicketList";
    }

}
