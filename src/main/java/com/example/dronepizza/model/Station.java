package com.example.dronepizza.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int station_ID;
    private double lat;
    private double lon;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("station-drones") // Specificer entydig værdi
    private List<Drone> drones = new ArrayList<>();

    public Station(){

    }

    public int getStation_ID() {
        return station_ID;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }
}
