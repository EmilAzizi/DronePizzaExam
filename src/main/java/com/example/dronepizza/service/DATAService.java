package com.example.dronepizza.service;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.repository.BERepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DATAService {
    private List<Drone> drones;
    private BERepository repository;

    public DATAService(BERepository repository){
        this.repository = repository;
    }

    public List getDronesFromRepository(){
        return repository.getAllDrones();
    }
}
