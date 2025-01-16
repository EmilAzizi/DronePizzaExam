package com.example.dronepizza.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pizza_ID;

    private String titel;
    private int pris;

}
