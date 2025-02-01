package com.ElevatorSystemSimulation.ElevatorSystem.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Elevator implements Runnable {
    private int id;
    private Direction direction;
    private int currentFloor;
    private BlockingQueue<Integer> destinationFloors;
    private boolean inMaintenance;
    private volatile boolean running;

    public Elevator(int id) {
        this.id = id;
        this.direction = Direction.IDLE;
        this.currentFloor = 0;
        this.destinationFloors = new LinkedBlockingQueue<>();
        this.inMaintenance = false;
        this.running = true;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addDestination(int floor) {
        if (!destinationFloors.contains(floor)) {
            destinationFloors.offer(floor);
            updateDirection();
        } 
    }

    public void updateDirection() {
        if (!destinationFloors.isEmpty()) {
            int nextFloor = destinationFloors.peek();
            direction = (nextFloor > currentFloor) ? Direction.UP : Direction.DOWN;
        } else {
            direction = Direction.IDLE;
        }
    }

    public void move() throws InterruptedException {
        if (!destinationFloors.isEmpty()) {
            int nextFloor = destinationFloors.peek();
            if (nextFloor > currentFloor) currentFloor += 1;
            else if (nextFloor < currentFloor) currentFloor -= 1;
            
            if (currentFloor == nextFloor) destinationFloors.poll();
            updateDirection();
        }
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                if(!destinationFloors.isEmpty()) {
                    move();
                    Thread.sleep(1000); 
                } else {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {

        }
    }
}
