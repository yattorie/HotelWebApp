package com.example.course.controllers;

import com.example.course.models.Guest;
import com.example.course.repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;
    @GetMapping("/guest")
    public String guest(Model model) {
        Iterable<Guest> guests = guestRepository.findAll();
        model.addAttribute("guests", guests);
        return "guest";
    }

    @GetMapping("/guest/add")
    public String guestAdd(Model model) {
        Iterable<Guest> guests = guestRepository.findAll();
        return "guest-add";
    }

    @PostMapping("/guest/add")
    public String guestPostAdd(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String middle_name, Model model) {
            Guest guest = new Guest(first_name, last_name, middle_name);
            guestRepository.save(guest);
        return "redirect:/guest";
    }

    @GetMapping("/guest/{id}")
    public String guestDetails(@PathVariable(value = "id") long id, Model model) {
        if (!guestRepository.existsById(id)) {
            return "redirect:/guest";
        }
        Optional<Guest> guest = guestRepository.findById(id);
        ArrayList<Guest> res = new ArrayList<>();
        guest.ifPresent(res::add);
        model.addAttribute("guest", res);
        return "guest-details";
    }

    @GetMapping("/guest/{id}/edit")
    public String guestEdit(@PathVariable(value = "id") long id, Model model) {
        if (!guestRepository.existsById(id)) {
            return "redirect:/guest";
        }
        Optional<Guest> guest = guestRepository.findById(id);
        ArrayList<Guest> res = new ArrayList<>();
        guest.ifPresent(res::add);
        model.addAttribute("guest", res);
        return "guest-edit";
    }

    @PostMapping("/guest/{id}/edit")
    public String guestPostUpdate(@PathVariable(value = "id") long id, @RequestParam String first_name, @RequestParam String last_name,@RequestParam String middle_name, Model model) {
        Guest guest = guestRepository.findById(id).orElseThrow();
        guest.setFirst_name(first_name);
        guest.setLast_name(last_name);
        guest.setMiddle_name(middle_name);
        guestRepository.save(guest);
        return "redirect:/guest";
    }
    @PostMapping("/guest/{id}/remove")
    public String guestPostDelete(@PathVariable(value = "id") long id, Model model) {
        Guest guest = guestRepository.findById(id).orElseThrow();
        guestRepository.delete(guest);
        return "redirect:/guest";
    }
}
