package com.example.dronepizza.repository;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Station;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

//BE = Back End
public class BERepository {

    private DroneInterface droneInterface;
    private LeveringInterface leveringInterface;
    private PizzaInterface pizzaInterface;
    private StationInterface stationInterface;

    private List<Drone> allDrones;

    private List<Station> allStations;

    public BERepository(DroneInterface droneInterface,
                        LeveringInterface leveringInterface,
                        PizzaInterface pizzaInterface,
                        StationInterface stationInterface){
        this.droneInterface = droneInterface;
        this.leveringInterface = leveringInterface;
        this.pizzaInterface = pizzaInterface;
        this.stationInterface = stationInterface;
    }

    public BERepository(){
    }

    public List<Drone> getAllDrones() {
        allDrones = droneInterface.findAll();

        return allDrones;
    }
}
