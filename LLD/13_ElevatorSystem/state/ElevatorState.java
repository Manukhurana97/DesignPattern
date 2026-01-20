package state;

import entities.Request;
import enums.Direction;
import service.Elevator;

public interface ElevatorState {
    void move(Elevator elevator);

    void addRequest(Elevator elevator, Request request);

    Direction getDirection();

}