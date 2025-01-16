package com.example.dronepizza.config;

import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Levering;
import com.example.dronepizza.model.Pizza;
import com.example.dronepizza.model.Station;
import com.example.dronepizza.repository.DroneInterface;
import com.example.dronepizza.repository.LeveringInterface;
import com.example.dronepizza.repository.PizzaInterface;
import com.example.dronepizza.repository.StationInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final DroneInterface droneInterface;
    private final LeveringInterface leveringInterface;
    private final PizzaInterface pizzaInterface;
    private final StationInterface stationInterface;

    public InitData(DroneInterface droneInterface,
                    LeveringInterface leveringInterface,
                    PizzaInterface pizzaInterface,
                    StationInterface stationInterface) {
        this.droneInterface = droneInterface;
        this.leveringInterface = leveringInterface;
        this.pizzaInterface = pizzaInterface;
        this.stationInterface = stationInterface;
    }

    @Override
    public void run(String... args) {
        List<Station> allStations = stationInterface.findAll();

        if(allStations.isEmpty()){
            Station station1 = new Station();
            station1.setLat(55.6756);
            station1.setLon(12.5653);
            stationInterface.save(station1);

            Station station2 = new Station();
            station2.setLat(55.6797);
            station2.setLon(12.5933);
            stationInterface.save(station2);

            Station station3 = new Station();
            station3.setLat(55.6802);
            station3.setLon(12.5667);
            stationInterface.save(station3);
        } else {

        }
        System.out.println("Testdata er blevet tilføjet!");
    }
}
