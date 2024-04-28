package com.example.course.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Serv")
public class Serv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ServID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Serv(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    public Serv() {
    }
}
