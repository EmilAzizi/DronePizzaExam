package com.example.dronepizza.repository;

import com.example.dronepizza.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationInterface extends JpaRepository<Station, Integer> {
}
