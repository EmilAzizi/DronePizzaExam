package com.example.dronepizza.repository;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Pizza;
import org.aspectj.apache.bcel.Repository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTEST {

    private BERepository repository;
    private StationInterface stationInterface;
    private PizzaInterface pizzaInterface;
    private DroneInterface droneInterface;
    private LeveringInterface leveringInterface;

    @BeforeEach
    void setUp(){
        repository = new BERepository();
    }

    @Test
    public void testGetAllDrones(){
        List<Drone> drones = droneInterface.findAll();

        List<Drone> getAllDrones = repository.getAllDrones();

        assertEquals(getAllDrones, null);
    }
}
