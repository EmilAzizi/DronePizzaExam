package com.example.dronepizza.controller;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Levering;
import com.example.dronepizza.model.Station;
import com.example.dronepizza.service.DATAService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;

@RestController
public class RESTController {

    private DATAService service;
    public RESTController(DATAService service){
        this.service = service;
    }

    @GetMapping("/api/drones")
    public List<Drone> parseDronesToJson(){
        List<Drone> allDrones = service.getDronesFromRepository();
        return allDrones;
    }

    @PostMapping("/api/drones/add")
    public Drone createDrone(@RequestBody Drone newDrone){
        service.createDrone(newDrone);
        return newDrone;
    }

    @GetMapping("/api/stations")
    public List<Station> getStations() {
        return service.getAllStations();
    }

    @PostMapping("/api/drones/enable/{id}")
    public String enableDrone(@PathVariable int id) {
        try {
            service.changeDroneToEnable(id);
            return "Drone med ID " + id + " er nu i drift.";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/api/drones/disable/{id}")
    public String disableDrone(@PathVariable int id) {
        try {
            service.changeDroneToDisable(id);
            return "Drone med ID " + id + " er nu i drift.";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/api/drones/retire/{id}")
    public String retireDrone(@PathVariable int id) {
        try {
            service.changeDroneToRetire(id);
            return "Drone med ID " + id + " er nu i drift.";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("api/deliveries")
    public List<Levering> parseDeliveriesToJson(){
        List<Levering> allDeliveries = service.getAllDeliveriesFromRepository();
        return allDeliveries;
    }

    @PostMapping("/api/deliveries/add")
    public Levering createDelivery(@RequestBody Levering newLevering){
        service.createDelivery(newLevering);
        return newLevering;
    }

    @GetMapping("/api/deliveries/queue")
    public List<Levering> getMissingDroneDeliveries(){
        return service.checkForMissingDrones();
    }
}
