package service;

import entities.Request;
import enums.Direction;
import enums.RequestSource;
import observer.ElevatorDisplay;
import strategy.ElevatorSelectionStrategy;
import strategy.NearestElevatorStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ElevatorService {
    private static ElevatorService INSTANCE;

    private final ExecutorService executorService;
    private final Map<Integer, Elevator> elevators;
    private final ElevatorSelectionStrategy selectionStrategy;

    public ElevatorService(int elevatorsCount) {
        this.selectionStrategy = new NearestElevatorStrategy();
        this.executorService = Executors.newFixedThreadPool(elevatorsCount);

        List<Elevator> elevatorList = new ArrayList<>();
        ElevatorDisplay display = new ElevatorDisplay();

        for (int i = 0; i < elevatorsCount; i++) {
            Elevator elevator = new Elevator(i);
            elevatorList.add(elevator);
            elevator.addObserver(display);
        }

        this.elevators = elevatorList.stream().collect(Collectors.toMap(Elevator::getId, e -> e));
    }

    public synchronized static ElevatorService getInstance(int elevators) {
        if (INSTANCE == null) {
            INSTANCE = new ElevatorService(elevators);
        }

        return INSTANCE;
    }

    public void start() {
        System.out.print("Starting elevator service...");
        for (Elevator elevator : elevators.values()) {
            executorService.submit(elevator);
        }
    }

    public void requestElevator(int requestedFloor, Direction direction) {
        System.out.println("---\n EXTERNAL Request: User at floor " + requestedFloor + " want to go " + direction);
        Request request = new Request(requestedFloor, direction, RequestSource.EXTERNAL);

        Optional<Elevator> assignedElevator = selectionStrategy.selectElevator(new ArrayList<>(elevators.values()), request);

        if (assignedElevator.isPresent()) {
            assignedElevator.get().addRequest(request);
        } else {
            System.out.print("all Elevator are busy, please try again later");
        }
    }


    public void selectFloor(int elevatorId, int to) {
        System.out.println("---\n Internal Request: User in elevator " + elevatorId + " want to go to " + to);
        Request request = new Request(to, Direction.IDLE, RequestSource.INTERNAL);

        Elevator elevator = elevators.get(elevatorId);
        if (elevator == null) {
            System.out.print("Invalid elevator id");
        } else {
            elevator.addRequest(request);
        }
    }


    public void shutdown() {
        System.out.print("Shutting down elevator system...");

        for (Elevator elevator : elevators.values()) {
            elevator.stopElevator();
        }

        executorService.shutdown();
    }
}