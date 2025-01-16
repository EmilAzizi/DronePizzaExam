package com.example.dronepizza.repository;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Levering;
import com.example.dronepizza.model.Station;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BERepository {

    private DroneInterface droneInterface;
    private LeveringInterface leveringInterface;
    private PizzaInterface pizzaInterface;
    private StationInterface stationInterface;

    private List<Drone> allDrones;

    private List<Station> allStations;

    private List<Levering> allDeliveries;

    public BERepository(DroneInterface droneInterface,
                        LeveringInterface leveringInterface,
                        PizzaInterface pizzaInterface,
                        StationInterface stationInterface){
        this.droneInterface = droneInterface;
        this.leveringInterface = leveringInterface;
        this.pizzaInterface = pizzaInterface;
        this.stationInterface = stationInterface;
    }

    public List<Drone> getAllDrones() {
        allDrones = droneInterface.findAll();
        return allDrones;
    }

    public List<Station> getAllStations(){
        allStations = stationInterface.findAll();
        return allStations;
    }

    public void saveDroneInDB(Drone droneToBeSaved){
        droneInterface.save(droneToBeSaved);
    }

    public List<Levering> getAllDeliveries(){
        allDeliveries = leveringInterface.findAll();
        return allDeliveries;
    }
}
