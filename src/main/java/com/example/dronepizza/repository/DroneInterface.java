package com.example.dronepizza.repository;

import com.example.dronepizza.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneInterface extends JpaRepository<Drone, Integer> {
}
