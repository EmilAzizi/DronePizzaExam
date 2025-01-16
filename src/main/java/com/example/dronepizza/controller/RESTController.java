package com.example.dronepizza.controller;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.service.DATAService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class RESTController {

    private DATAService service;
    public RESTController(DATAService service){
        this.service = service;
    }

    @GetMapping("/api/drones")
    public List parseDronesToJson(){
        List<Drone> allDrones = service.getDronesFromRepository();
        return allDrones;
    }
}
