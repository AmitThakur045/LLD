package com.ElevatorSystemSimulation.ElevatorSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Direction;
import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;

import jakarta.annotation.PostConstruct;

@Service
public class ElevatorService {
    private List<Elevator> elevators;
    private ElevatorSelectionStrategy selectionStrategy;
    private ElevatorControlStrategy controlStrategy;

    public ElevatorService(OddEvenStrategy selectionStrategy, FirstComeFirstServeStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
        this.selectionStrategy = selectionStrategy;

        this.elevators = new ArrayList<>();
        initializeElevators(3); 
    }

    @PostConstruct
    public void initializeElevators(int elevatorCounts) {
        for (int i=0; i<elevatorCounts; i++) {
            Elevator elevator = new Elevator(i+1);
            new Thread(elevator).start();
            this.elevators.add(elevator);
        }
    }

    public void requestElevator(int floor, Direction direction) {
        Elevator selectedElevator = selectionStrategy.selectedElevator(elevators, floor, direction);
        if (selectedElevator != null) {
            controlStrategy.handleRequest(selectedElevator, floor);
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void shutdownElevators() {
        for (Elevator elevator: elevators) {
            elevator.stop();
        }
    }
}
