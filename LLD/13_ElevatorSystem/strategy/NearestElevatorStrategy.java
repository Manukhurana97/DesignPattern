package strategy;


import entities.Request;
import service.Elevator;
import enums.*;

import java.util.List;
import java.util.Optional;


public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
	public Optional<Elevator> selectElevator(List<Elevator> elevators, Request request) {
		Elevator nearestElevator = null;
		int minDistance = Integer.MAX_VALUE;

		for(Elevator elevator: elevators) {
			if(isSuitable(elevator, request)) {
				int distance = Math.abs(elevator.getCurrentFloor() - request.getTargetFloor());

				if(distance < minDistance) {
					minDistance = distance;
					nearestElevator = elevator;
				}
			}
		}

		return Optional.ofNullable(nearestElevator);
	}

	public boolean isSuitable(Elevator elevator, Request request) {
		if(elevator.getDirection() == Direction.IDLE) return true;

		if(elevator.getDirection() == request.getDirection()) {
			if(elevator.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getTargetFloor()) {
				return true;
			}
            return elevator.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getTargetFloor();
		}

		return false;
	}
}