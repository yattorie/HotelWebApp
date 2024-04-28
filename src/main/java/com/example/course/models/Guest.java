package com.example.course.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GuestID")
    private int id;
    @Column(name = "FirstName")
    private String first_name;
    @Column(name = "LastName")
    private String last_name;
    @Column(name = "MiddleName")
    private String middle_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Guest(String first_name, String last_name, String middle_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
    }

    public Guest() {
    }
}

