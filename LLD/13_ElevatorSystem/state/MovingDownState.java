package state;

import entities.Request;
import enums.Direction;
import enums.RequestSource;
import service.Elevator;

public class MovingDownState implements ElevatorState {

    public void move(Elevator elevator) {
        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getUpRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

        if (elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getUpRequests().pollFirst();
        }

        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }


    public void addRequest(Elevator elevator, Request request) {
        if (request.getSource() == RequestSource.INTERNAL) {
            if (request.getTargetFloor() < elevator.getCurrentFloor()) {
                elevator.getDownRequests().add(request.getTargetFloor());
            } else {
                elevator.getUpRequests().add(request.getTargetFloor());
            }

            return;
        }

        if (request.getDirection() == Direction.DOWN && request.getTargetFloor() <= elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.UP) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
    }


    public Direction getDirection() {
        return Direction.DOWN;
    }
}