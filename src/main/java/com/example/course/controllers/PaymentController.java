package com.example.course.controllers;

import com.example.course.models.Payment;
import com.example.course.repo.PaymentRepository;
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
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @GetMapping("/payment")
    public String payment(Model model) {
        Iterable<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "payment";
    }

    @GetMapping("/payment/add")
    public String paymentAdd(Model model) {
        Iterable<Payment> payments = paymentRepository.findAll();
        return "payment-add";
    }

    @PostMapping("/payment/add")
    public String paymentPostAdd(@RequestParam BigDecimal amount, Model model) {
        Payment payments = new Payment(amount);
        paymentRepository.save(payments);
        return "redirect:/payment";
    }

    @GetMapping("/payment/{id}")
    public String paymentDetails(@PathVariable(value = "id") long id, Model model) {
        if (!paymentRepository.existsById(id)) {
            return "redirect:/payment";
        }
        Optional<Payment> payment = paymentRepository.findById(id);
        ArrayList<Payment> esr = new ArrayList<>();
        payment.ifPresent(esr::add);
        model.addAttribute("payment", esr);
        return "payment-details";
    }

    @GetMapping("/payment/{id}/edit")
    public String paymentEdit(@PathVariable(value = "id") long id, Model model) {
        if (!paymentRepository.existsById(id)) {
            return "redirect:/payment";
        }
        Optional<Payment> payment = paymentRepository.findById(id);
        ArrayList<Payment> esr = new ArrayList<>();
        payment.ifPresent(esr::add);
        model.addAttribute("payment", esr);
        return "payment-edit";
    }

    @PostMapping("/payment/{id}/edit")
    public String paymentPostUpdate(@PathVariable(value = "id") long id, @RequestParam BigDecimal amount, Model model) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        payment.setAmount(amount);
        paymentRepository.save(payment);
        return "redirect:/payment";
    }
    @PostMapping("/payment/{id}/remove")
    public String paymentPostDelete(@PathVariable(value = "id") long id, Model model) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        paymentRepository.delete(payment);
        return "redirect:/payment";
    }

}
