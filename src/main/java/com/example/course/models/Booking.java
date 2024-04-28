package com.example.course.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BookingID")
    private int id;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Booking(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Booking() {
    }
}
