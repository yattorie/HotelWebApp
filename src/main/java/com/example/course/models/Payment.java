package com.example.course.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PaymentID")
    private int id;
    @Column(name = "amount")
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Payment(BigDecimal amount) {
        this.amount = amount;

    }
    public Payment() {
    }
}

