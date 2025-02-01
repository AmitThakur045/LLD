package com.ElevatorSystemSimulation.ElevatorSystem.service;

import org.springframework.stereotype.Component;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;

@Component
public class FirstComeFirstServeStrategy implements ElevatorControlStrategy{
    @Override
    public void handleRequest(Elevator elevator, int floor) {
        elevator.addDestination(floor);
    }
}
