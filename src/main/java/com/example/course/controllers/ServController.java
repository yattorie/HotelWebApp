package com.example.course.controllers;

import com.example.course.models.Serv;
import com.example.course.repo.ServRepository;
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
public class ServController {
    @Autowired
    private ServRepository servRepository;
    @GetMapping("/serv")
    public String serv(Model model) {
        Iterable<Serv> servs = servRepository.findAll();
        model.addAttribute("servs", servs);
        return "serv";
    }

    @GetMapping("/serv/add")
    public String servAdd(Model model) {
        Iterable<Serv> servs = servRepository.findAll();
        return "serv-add";
    }

    @PostMapping("/serv/add")
    public String servPostAdd(@RequestParam String name, @RequestParam BigDecimal price, Model model) {
        Serv servs = new Serv(name, price);
        servRepository.save(servs);
        return "redirect:/serv";

    }

    @GetMapping("/serv/{id}")
    public String servDetails(@PathVariable(value = "id") long id, Model model) {
        if (!servRepository.existsById(id)) {
            return "redirect:/serv";
        }
        Optional<Serv> serv = servRepository.findById(id);
        ArrayList<Serv> ser = new ArrayList<>();
        serv.ifPresent(ser::add);
        model.addAttribute("serv", ser);
        return "serv-details";
    }

    @GetMapping("/serv/{id}/edit")
    public String servEdit(@PathVariable(value = "id") long id, Model model) {
        if (!servRepository.existsById(id)) {
            return "redirect:/serv";
        }
        Optional<Serv> serv = servRepository.findById(id);
        ArrayList<Serv> ser = new ArrayList<>();
        serv.ifPresent(ser::add);
        model.addAttribute("serv", ser);
        return "serv-edit";
    }

    @PostMapping("/serv/{id}/edit")
    public String servPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam BigDecimal price, Model model) {
        Serv serv = servRepository.findById(id).orElseThrow();
        serv.setName(name);
        serv.setPrice(price);
        servRepository.save(serv);
        return "redirect:/serv";
    }
    @PostMapping("/serv/{id}/remove")
    public String servPostDelete(@PathVariable(value = "id") long id, Model model) {
        Serv serv = servRepository.findById(id).orElseThrow();
        servRepository.delete(serv);
        return "redirect:/serv";
    }

}
