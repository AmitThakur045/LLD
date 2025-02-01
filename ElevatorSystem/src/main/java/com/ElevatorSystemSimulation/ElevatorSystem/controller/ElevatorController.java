package com.ElevatorSystemSimulation.ElevatorSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ElevatorSystemSimulation.ElevatorSystem.model.Direction;
import com.ElevatorSystemSimulation.ElevatorSystem.model.Elevator;
import com.ElevatorSystemSimulation.ElevatorSystem.service.ElevatorService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;
    
    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @PostMapping("/request")
    public String requestElevator(@RequestParam int floor, @RequestParam Direction direction) {
        elevatorService.requestElevator(floor, direction);
        return "Elevator requested to floor " + floor + " in direction " + direction;
    }

    @GetMapping("/status")
    public List<Elevator> getElevators() {
        return elevatorService.getElevators();
    }
    
    @PostMapping("/shutdown")
    public String shutdownElevators() {
        elevatorService.shutdownElevators();
        return "All elevators stopped.";
    }
}
