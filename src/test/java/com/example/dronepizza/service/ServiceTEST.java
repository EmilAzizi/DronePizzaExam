package com.example.dronepizza.service;

import com.example.dronepizza.model.DriftStatus;
import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Station;
import com.example.dronepizza.repository.BERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTEST {

    @Mock
    private BERepository repository;

    @InjectMocks
    private DATAService dataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDrone_NoStations() {
        // Arrange
        List<Station> mockStations = new ArrayList<>(); // Ingen stationer
        when(repository.getAllStations()).thenReturn(mockStations);

        Drone testDrone = new Drone();

        // Act
        dataService.createDrone(testDrone);

        // Assert
        verify(repository, never()).saveDroneInDB(any(Drone.class)); // Dronen skal ikke gemmes
        assertNull(testDrone.getStation(), "Station should not be set for the drone.");
        assertNull(testDrone.getDriftStatus(), "DriftStatus should not be set for the drone.");
    }

    @Test
    public void testCreateDrone_WithStations() {
        // Arrange
        List<Station> mockStations = new ArrayList<>();
        Station station1 = new Station();
        mockStations.add(station1);

        when(repository.getAllStations()).thenReturn(mockStations);

        Drone testDrone = new Drone();

        // Act
        dataService.createDrone(testDrone);

        // Assert
        verify(repository, times(1)).saveDroneInDB(testDrone); // Dronen skal gemmes
        assertEquals(station1, testDrone.getStation(), "Drone should be assigned to the station.");
        assertEquals(DriftStatus.I_DRIFT, testDrone.getDriftStatus(), "DriftStatus should be set to I_DRIFT.");
    }

    @Test
    public void testFindStationWithLowestDrones() {
        // Arrange
        List<Station> mockStations = new ArrayList<>();
        Station station1 = new Station();
        station1.setDrones(new ArrayList<>()); // Ingen droner
        Station station2 = new Station();
        List<Drone> station2Drones = new ArrayList<>();
        station2Drones.add(new Drone());
        station2.setDrones(station2Drones); // 1 drone
        mockStations.add(station1);
        mockStations.add(station2);

        // Act
        Station result = dataService.findStationWithLowestDrones(mockStations);

        // Assert
        assertEquals(station1, result, "Station with the fewest drones should be returned.");
    }

    @Test
    public void testFindStationWithLowestDrones_EmptyStations() {
        // Arrange
        List<Station> mockStations = new ArrayList<>();

        // Act
        Station result = dataService.findStationWithLowestDrones(mockStations);

        // Assert
        assertNull(result, "Should return null when no stations are available.");
    }
}
