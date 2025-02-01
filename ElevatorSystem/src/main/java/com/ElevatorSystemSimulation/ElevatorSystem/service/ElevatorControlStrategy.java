package com.ElevatorSystemSimulation.ElevatorSystem.service;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;

public interface ElevatorControlStrategy {
    void handleRequest(Elevator elevator, int floor);
}
