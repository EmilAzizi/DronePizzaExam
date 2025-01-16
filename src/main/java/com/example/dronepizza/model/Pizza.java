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

    public Pizza(){

    }

    public int getPizza_ID() {
        return pizza_ID;
    }

    public String getTitel() {
        return titel;
    }

    public int getPris() {
        return pris;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}
