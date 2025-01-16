package com.example.dronepizza.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Levering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int levering_ID;

    private String adresse;
    private String forventet_levering;
    private String faktisk_levering;

    @ManyToOne
    @JoinColumn(name = "drone_ID")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "pizza_ID")
    private Pizza pizza;
}
