package com.example.dronepizza.repository;

import com.example.dronepizza.model.Levering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeveringInterface extends JpaRepository<Levering, Integer> {
}
