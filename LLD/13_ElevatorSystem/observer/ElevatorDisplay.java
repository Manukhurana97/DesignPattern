package observer;

import service.Elevator;

public class ElevatorDisplay implements ElevatorObserver {
    public void update(Elevator elevator) {
        System.out.println("['Display'] Elevator " + elevator.getId() + " | current Floor: " + elevator.getCurrentFloor() + " | Direction: " + elevator.getDirection());
    }
}