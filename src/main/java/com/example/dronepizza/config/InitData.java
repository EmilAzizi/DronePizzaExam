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
        List<Pizza> allPizzas = pizzaInterface.findAll();

        if(allStations.isEmpty()){
            Station station1 = new Station();
            station1.setLat("55,41N");
            station1.setLon("12,34E");
            stationInterface.save(station1);

            Station station2 = new Station();
            station2.setLat("55,42N");
            station2.setLon("12,36E");
            stationInterface.save(station2);

            Station station3 = new Station();
            station3.setLat("55,40N");
            station3.setLon("12,33E");
            stationInterface.save(station3);

        } else {

        }

        if(allPizzas.isEmpty()){
            Pizza margherita = new Pizza();
            margherita.setTitel("Margherita");
            margherita.setPris(60);
            pizzaInterface.save(margherita);

            Pizza pepperoni = new Pizza();
            pepperoni.setTitel("Pepperoni");
            pepperoni.setPris(70);
            pizzaInterface.save(pepperoni);

            Pizza hawaiian = new Pizza();
            hawaiian.setTitel("Hawaiian");
            hawaiian.setPris(75);
            pizzaInterface.save(hawaiian);

            Pizza veggie = new Pizza();
            veggie.setTitel("Veggie");
            veggie.setPris(65);
            pizzaInterface.save(veggie);

            Pizza bbqChicken = new Pizza();
            bbqChicken.setTitel("BBQ Chicken");
            bbqChicken.setPris(80);
            pizzaInterface.save(bbqChicken);
        }
        System.out.println("Testdata er blevet tilføjet!");
    }
}
