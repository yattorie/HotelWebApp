package com.example.course.controllers;

import com.example.course.models.Room;
import com.example.course.repo.RoomRepository;
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
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @GetMapping("/room")
    public String room(Model model) {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room";
    }

    @GetMapping("/room/add")
    public String roomAdd(Model model) {
        Iterable<Room> rooms = roomRepository.findAll();
        return "room-add";
    }

    @PostMapping("/room/add")
    public String roomPostAdd(@RequestParam String roomNumber, @RequestParam String roomType, @RequestParam int amount, @RequestParam BigDecimal price, @RequestParam String description, Model model) {
        Room rooms = new Room(roomNumber, roomType, amount, price, description);
        roomRepository.save(rooms);
        return "redirect:/room";
    }

    @GetMapping("/room/{id}")
    public String roomDetails(@PathVariable(value = "id") long id, Model model) {
        if (!roomRepository.existsById(id)) {
            return "redirect:/room";
        }
        Optional<Room> room = roomRepository.findById(id);
        ArrayList<Room> ser = new ArrayList<>();
        room.ifPresent(ser::add);
        model.addAttribute("room", ser);
        return "room-details";
    }

    @GetMapping("/room/{id}/edit")
    public String roomEdit(@PathVariable(value = "id") long id, Model model) {
        if (!roomRepository.existsById(id)) {
            return "redirect:/room";
        }
        Optional<Room> room = roomRepository.findById(id);
        ArrayList<Room> ser = new ArrayList<>();
        room.ifPresent(ser::add);
        model.addAttribute("room", ser);
        return "room-edit";
    }

    @PostMapping("/room/{id}/edit")
    public String roomPostUpdate(@PathVariable(value = "id") long id, @RequestParam String roomNumber, @RequestParam String roomType, @RequestParam int amount, @RequestParam BigDecimal price, @RequestParam String description, Model model) {
        Room room = roomRepository.findById(id).orElseThrow();
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
        room.setAmount(amount);
        room.setPrice(price);
        room.setDescription(description);
        roomRepository.save(room);
        return "redirect:/room";
    }
    @PostMapping("/room/{id}/remove")
    public String roomPostDelete(@PathVariable(value = "id") long id, Model model) {
        Room room = roomRepository.findById(id).orElseThrow();
        roomRepository.delete(room);
        return "redirect:/room";
    }

}
