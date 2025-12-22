package strategy.parking;

import java.util.*;

import entity.*;
import parking.*;


public class NearestParking implements ParkingStategy {
	public Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> floors, Vehicle vehicle) {
		for(ParkingFloor floor: floors) {
			Optional<ParkingSpot> spot = floor.getSpot(vehicle);
			if(spot.isPresent()) {
				return spot;
			}
		}

		return Optional.empty();
	}
}