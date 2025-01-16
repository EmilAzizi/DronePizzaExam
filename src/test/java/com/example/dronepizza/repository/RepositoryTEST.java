package com.example.dronepizza.repository;

import com.example.dronepizza.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RepositoryTEST {

    @Mock
    private DroneInterface droneInterface;

    @Mock
    private StationInterface stationInterface;

    @InjectMocks
    private BERepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDrone() {
    }
}
