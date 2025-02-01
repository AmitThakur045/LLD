package com.ElevatorSystemSimulation.ElevatorSystem.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Direction;
import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;

@Component
public class OddEvenStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectedElevator(List<Elevator> elevators, int requestedFloor, Direction direction) {
        return elevators.stream()
                .filter(e -> !e.isInMaintenance() && 
                        e.getId() % 2 == requestedFloor % 2)
                .findFirst().orElse(null);
    }
}
