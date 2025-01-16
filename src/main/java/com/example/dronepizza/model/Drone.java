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

    public Drone(){

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

    public String getDriftStatus() {
        return driftStatus;
    }

    public String getSerial_UUID() {
        return serial_UUID;
    }

    public void setDrone_ID(int drone_ID) {
        this.drone_ID = drone_ID;
    }

    public void setDriftStatus(String driftStatus) {
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
