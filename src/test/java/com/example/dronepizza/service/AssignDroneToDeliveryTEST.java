package com.example.dronepizza.service;

import com.example.dronepizza.model.DriftStatus;
import com.example.dronepizza.model.Drone;
import com.example.dronepizza.model.Levering;
import com.example.dronepizza.repository.BERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssignDroneToDeliveryTEST {

    @Mock
    private BERepository repository;

    @InjectMocks
    private DATAService dataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAssignDroneToDelivery_Success() {
        List<Levering> mockDeliveries = new ArrayList<>();
        Levering levering = new Levering();
        levering.setLevering_ID(1);
        mockDeliveries.add(levering);

        List<Drone> mockDrones = new ArrayList<>();
        Drone availableDrone = new Drone();
        availableDrone.setDrone_ID(101);
        availableDrone.setDriftStatus(DriftStatus.I_DRIFT);
        mockDrones.add(availableDrone);

        when(repository.getAllDeliveries()).thenReturn(mockDeliveries);
        when(repository.getAllDrones()).thenReturn(mockDrones);

        dataService.assignDroneToDelivery(1);

        verify(repository, times(1)).saveDelivery(levering);
        assertEquals(availableDrone, levering.getDrone(), "Drone should be assigned to the delivery.");
    }

    @Test
    public void testAssignDroneToDelivery_NoAvailableDrones() {
        List<Levering> mockDeliveries = new ArrayList<>();
        Levering levering = new Levering();
        levering.setLevering_ID(1);
        mockDeliveries.add(levering);

        List<Drone> mockDrones = new ArrayList<>();
        when(repository.getAllDeliveries()).thenReturn(mockDeliveries);
        when(repository.getAllDrones()).thenReturn(mockDrones);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            dataService.assignDroneToDelivery(1);
        });
        assertEquals("Ingen droner tilgængelige til levering.", exception.getMessage());
    }

    @Test
    public void testAssignDroneToDelivery_AlreadyHasDrone() {
        List<Levering> mockDeliveries = new ArrayList<>();
        Levering levering = new Levering();
        levering.setLevering_ID(1);

        Drone assignedDrone = new Drone();
        levering.setDrone(assignedDrone);
        mockDeliveries.add(levering);

        when(repository.getAllDeliveries()).thenReturn(mockDeliveries);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            dataService.assignDroneToDelivery(1);
        });
        assertEquals("Leveringen har allerede en drone.", exception.getMessage());
    }

    @Test
    public void testAssignDroneToDelivery_InvalidDeliveryID() {
        List<Levering> mockDeliveries = new ArrayList<>();
        when(repository.getAllDeliveries()).thenReturn(mockDeliveries);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataService.assignDroneToDelivery(99); // Ugyldigt ID
        });
        assertEquals("Levering med ID 99 blev ikke fundet.", exception.getMessage());
    }
}
