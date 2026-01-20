package service;

import entities.Request;
import enums.Direction;
import observer.ElevatorObserver;
import state.ElevatorState;
import state.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable {
    private final int id;
    private AtomicInteger currentFloor;
    private boolean isRunning;
    private ElevatorState state;

    private TreeSet<Integer> upRequests;
    private TreeSet<Integer> downRequests;
    private List<ElevatorObserver> observers;


    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(1);
        this.isRunning = true;

        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
        this.state = new IdleState();
        this.observers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
        observer.update(this);
    }

    public void notifyObservers() {
        for(ElevatorObserver observer: observers) {
            observer.update(this);
        }
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void move() {
        state.move(this);
    }

    public void addRequest(Request request) {
        System.out.println("Elevator " + id + " processing " + request);
        state.addRequest(this, request);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor.set(floor);
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public TreeSet<Integer> getUpRequests() {
        return upRequests;
    }

    public TreeSet<Integer> getDownRequests() {
        return downRequests;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopElevator() {
        this.isRunning = false;
    }

    public void run() {
        while (!isRunning) {
            move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.isRunning = false;
            }
        }
    }

}