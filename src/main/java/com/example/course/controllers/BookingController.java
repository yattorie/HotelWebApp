package com.example.course.controllers;

import com.example.course.models.Booking;
import com.example.course.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @GetMapping("/booking")
    public String booking(Model model) {
        Iterable<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "booking";
    }

    @GetMapping("/booking/add")
    public String bookingAdd(Model model) {
        Iterable<Booking> bookings = bookingRepository.findAll();
        return "booking-add";
    }

    @PostMapping("/booking/add")
    public String bookingPostAdd(@RequestParam BigDecimal totalAmount, Model model) {
            Booking booking = new Booking(totalAmount);
            bookingRepository.save(booking);
        return "redirect:/booking";
    }

    @GetMapping("/booking/{id}")
    public String bookingDetails(@PathVariable(value = "id") long id, Model model) {
        if (!bookingRepository.existsById(id)) {
            return "redirect:/booking";
        }
        Optional<Booking> booking = bookingRepository.findById(id);
        ArrayList<Booking> res = new ArrayList<>();
        booking.ifPresent(res::add);
        model.addAttribute("booking", res);
        return "booking-details";
    }

    @GetMapping("/booking/{id}/edit")
    public String bookingEdit(@PathVariable(value = "id") long id, Model model) {
        if (!bookingRepository.existsById(id)) {
            return "redirect:/booking";
        }
        Optional<Booking> booking = bookingRepository.findById(id);
        ArrayList<Booking> res = new ArrayList<>();
        booking.ifPresent(res::add);
        model.addAttribute("bookings", res);
        return "booking-edit";
    }

    @PostMapping("/booking/{id}/edit")
    public String bookingPostUpdate(@PathVariable(value = "id") long id, @RequestParam BigDecimal totalAmount, Model model) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setTotalAmount(totalAmount);
        bookingRepository.save(booking);
        return "redirect:/booking";
    }
    @PostMapping("/booking/{id}/remove")
    public String bookingPostDelete(@PathVariable(value = "id") long id, Model model) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        bookingRepository.delete(booking);
        return "redirect:/booking";
    }
}
