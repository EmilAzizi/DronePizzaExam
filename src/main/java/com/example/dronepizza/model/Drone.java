package com.example.dronepizza.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int drone_ID;

    private String serial_UUID;

    private String driftStatus;

    @ManyToOne
    @JoinColumn(name = "station_ID")
    private Station station;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Levering> leveringList = new ArrayList<>();
}
