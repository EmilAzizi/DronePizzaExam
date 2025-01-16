package com.example.dronepizza.service;

import com.example.dronepizza.model.*;
import com.example.dronepizza.repository.BERepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DATAService {
    private List<Drone> drones;
    private BERepository repository;

    private List<Drone> allDrones;
    private List<Station> allStations;

    private List<Levering> allDeliveries;

    private List<Levering> deliveriesMissingDrone = new ArrayList<>();

    public DATAService(BERepository repository){
        this.repository = repository;
    }

    public List getDronesFromRepository(){
        return repository.getAllDrones();
    }

    public void createDrone(Drone drone){
        Drone droneToSaveInDB = drone;
        allStations = repository.getAllStations();

        if(allStations.isEmpty()){

        } else {
            Station fewestStationWithDrones = findStationWithLowestDrones(allStations);
            droneToSaveInDB.setStation(fewestStationWithDrones);
            droneToSaveInDB.setDriftStatus(DriftStatus.I_DRIFT);
            repository.saveDroneInDB(droneToSaveInDB);
        }
    }

    public Station findStationWithLowestDrones(List<Station> allStations){
        if(allStations.isEmpty()){
            return null;
        }

        Station stationWithFewestDrones = allStations.get(0);
        int fewestDrones = stationWithFewestDrones.getDrones().size();

        for(Station station : allStations){
            int droneCount = station.getDrones().size();
            if(droneCount < fewestDrones){
                stationWithFewestDrones = station;
                fewestDrones = droneCount;
            }
        }

        return stationWithFewestDrones;
    }

    public List<Station> getAllStations(){
        return repository.getAllStations();
    }

    public void changeDroneToEnable(int ID){
        Drone droneToChangeStatus = null;
        allDrones = repository.getAllDrones();
        for(Drone drone : allDrones){
            if(drone.getDrone_ID() == ID){
                droneToChangeStatus = drone;
            }
        }

        if(!droneToChangeStatus.equals(null)){
            if(droneToChangeStatus.getDriftStatus() != DriftStatus.I_DRIFT){
                droneToChangeStatus.setDriftStatus(DriftStatus.I_DRIFT);
                repository.saveDroneInDB(droneToChangeStatus);
            }
        }
    }

    public void changeDroneToDisable(int ID){
        Drone droneToChangeStatus = null;
        allDrones = repository.getAllDrones();
        for(Drone drone : allDrones){
            if(drone.getDrone_ID() == ID){
                droneToChangeStatus = drone;
            }
        }

        if(!droneToChangeStatus.equals(null)){
            if(droneToChangeStatus.getDriftStatus() != DriftStatus.UDE_AF_DRIFT){
                droneToChangeStatus.setDriftStatus(DriftStatus.UDE_AF_DRIFT);
                repository.saveDroneInDB(droneToChangeStatus);
            }
        }
    }

    public void changeDroneToRetire(int ID){
        Drone droneToChangeStatus = null;
        allDrones = repository.getAllDrones();
        for(Drone drone : allDrones){
            if(drone.getDrone_ID() == ID){
                droneToChangeStatus = drone;
            }
        }

        if(!droneToChangeStatus.equals(null)){
            if(droneToChangeStatus.getDriftStatus() != DriftStatus.UDFASET){
                droneToChangeStatus.setDriftStatus(DriftStatus.UDFASET);
                repository.saveDroneInDB(droneToChangeStatus);
            }
        }
    }

    public List<Levering> getAllDeliveriesFromRepository(){
        allDeliveries = repository.getAllDeliveries();
        return allDeliveries;
    }

    public void createDelivery(Levering levering) {
        Levering leveringToSaveInDB = levering;

        leveringToSaveInDB.setStatus(LeveringStatus.IKKE_LEVERET);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expectedDeliveryTime = currentTime.plusMinutes(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        leveringToSaveInDB.setForventet_levering(expectedDeliveryTime.format(formatter));

        List<Pizza> allPizzas = repository.getAllPizzas();
        if (allPizzas.isEmpty()) {
            throw new IllegalStateException("Ingen pizzaer tilgængelige i systemet.");
        }
        Random random = new Random();
        Pizza randomPizza = allPizzas.get(random.nextInt(allPizzas.size()));
        leveringToSaveInDB.setPizza(randomPizza);

        repository.saveDelivery(leveringToSaveInDB);
    }


    public List<Levering> checkForMissingDrones(){
        deliveriesMissingDrone.clear();
        allDeliveries = repository.getAllDeliveries();
        for(Levering levering : allDeliveries){
            if(levering.getDrone() == null && !deliveriesMissingDrone.contains(levering)){
                deliveriesMissingDrone.add(levering);
            }
        }
        return deliveriesMissingDrone;
    }

    public void assignDroneToDelivery(int deliveryID) {
        Levering deliveryToAssignDroneTo = null;
        allDeliveries = repository.getAllDeliveries();

        for (Levering levering : allDeliveries) {
            if (levering.getLevering_ID() == deliveryID) {
                if (levering.getDrone() != null) {
                    throw new IllegalStateException("Leveringen har allerede en drone.");
                }
                deliveryToAssignDroneTo = levering;
                break;
            }
        }

        if (deliveryToAssignDroneTo == null) {
            throw new IllegalArgumentException("Levering med ID " + deliveryID + " blev ikke fundet.");
        }

        allDrones = repository.getAllDrones();
        if (allDrones.stream().noneMatch(drone -> drone.getDriftStatus() == DriftStatus.I_DRIFT && drone.getLeveringList().isEmpty())) {
            throw new IllegalStateException("Ingen droner tilgængelige til levering.");
        }

        boolean droneFound = false;
        Random random = new Random();

        while (!droneFound) {
            Drone randomDrone = allDrones.get(random.nextInt(allDrones.size()));

            if (randomDrone.getDriftStatus() == DriftStatus.I_DRIFT && randomDrone.getLeveringList().isEmpty()) {
                deliveryToAssignDroneTo.setDrone(randomDrone);
                repository.saveDelivery(deliveryToAssignDroneTo);
                droneFound = true;
            }
        }
    }

    public void finishDelivery(int ID) {
        Levering deliveryToFinish = null;
        allDeliveries = repository.getAllDeliveries();

        for (Levering delivery : allDeliveries) {
            if (delivery.getLevering_ID() == ID) {
                if (delivery.getDrone() == null) {
                    throw new IllegalArgumentException("Levering med ID " + ID + " har ikke en drone tildelt.");
                }
                deliveryToFinish = delivery;
                break;
            }
        }

        if (deliveryToFinish == null) {
            throw new IllegalArgumentException("Levering med ID " + ID + " blev ikke fundet.");
        }

        if(deliveryToFinish.getStatus() == LeveringStatus.LEVERET){
            throw new IllegalArgumentException("Leveringen er allerede færdig.");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expectedDeliveryTime = currentTime.plusMinutes(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        deliveryToFinish.setFaktisk_levering(expectedDeliveryTime.format(formatter));
        deliveryToFinish.setStatus(LeveringStatus.LEVERET);
        repository.saveDelivery(deliveryToFinish);
    }


}
