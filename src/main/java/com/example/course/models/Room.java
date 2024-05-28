package com.example.course.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoomID")
    private int id;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @Column(name = "RoomType")
    private String roomType;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "Room_Service",
            joinColumns = @JoinColumn(name = "RoomID"),
            inverseJoinColumns = @JoinColumn(name = "ServID")
    )
    private List<Serv> services = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Room(String roomNumber, String roomType, int amount, BigDecimal price, String description) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amount = amount;
        this.price = price;
        this.description = description;
    }

    public Room() {
    }
}

