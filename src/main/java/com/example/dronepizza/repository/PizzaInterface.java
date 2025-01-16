package com.example.dronepizza.repository;

import com.example.dronepizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaInterface extends JpaRepository<Pizza, Integer> {
}
