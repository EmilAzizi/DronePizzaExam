package com.example.dronepizza.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Levering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levering_ID;

    private String status;

    private String adresse;
    private String forventet_levering;
    private String faktisk_levering;

    @ManyToOne
    @JoinColumn(name = "drone_ID")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "pizza_ID")
    private Pizza pizza;

    public Levering(){

    }

    public int getLevering_ID() {
        return levering_ID;
    }

    public Drone getDrone() {
        return drone;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getFaktisk_levering() {
        return faktisk_levering;
    }

    public String getForventet_levering() {
        return forventet_levering;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setFaktisk_levering(String faktisk_levering) {
        this.faktisk_levering = faktisk_levering;
    }

    public void setForventet_levering(String forventet_levering) {
        this.forventet_levering = forventet_levering;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
