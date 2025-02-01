package com.ElevatorSystemSimulation.ElevatorSystem.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Direction;
import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;

@Component
public interface ElevatorSelectionStrategy {    
    Elevator selectedElevator(List<Elevator> elevators, int requestedFloor, Direction direction);
}
