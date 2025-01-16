package com.example.dronepizza.config;

import com.example.dronepizza.model.Station;
import com.example.dronepizza.repository.DroneInterface;
import com.example.dronepizza.repository.LeveringInterface;
import com.example.dronepizza.repository.PizzaInterface;
import com.example.dronepizza.repository.StationInterface;
import org.springframework.stereotype.Component;

@Component
public class InitData {
    private DroneInterface droneInterface;
    private PizzaInterface pizzaInterface;
    private LeveringInterface leveringInterface;
    private StationInterface stationInterface;
}
