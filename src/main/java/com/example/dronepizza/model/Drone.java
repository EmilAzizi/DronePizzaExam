package com.example.dronepizza.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int drone_ID;

    private String serial_UUID;

    @Enumerated(EnumType.STRING)
    private DriftStatus driftStatus;

    @ManyToOne
    @JoinColumn(name = "station_ID")
    @JsonBackReference("station-drones") // Specificer entydig værdi
    private Station station;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("drone-levering") // Specificer entydig værdi
    private List<Levering> leveringList = new ArrayList<>();

    public Drone(){
        this.serial_UUID = UUID.randomUUID().toString();
    }

    public int getDrone_ID() {
        return drone_ID;
    }

    public List<Levering> getLeveringList() {
        return leveringList;
    }

    public Station getStation() {
        return station;
    }

    public DriftStatus getDriftStatus() {
        return driftStatus;
    }

    public String getSerial_UUID() {
        return serial_UUID;
    }

    public void setDrone_ID(int drone_ID) {
        this.drone_ID = drone_ID;
    }

    public void setDriftStatus(DriftStatus driftStatus) {
        this.driftStatus = driftStatus;
    }

    public void setLeveringList(List<Levering> leveringList) {
        this.leveringList = leveringList;
    }

    public void setSerial_UUID(String serial_UUID) {
        this.serial_UUID = serial_UUID;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
