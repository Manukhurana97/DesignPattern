package parking;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import entity.*;

public class ParkingFloor {

	private final int floorNumber;
	private final Map<String, ParkingSpot> spots;

	public ParkingFloor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.spots = new ConcurrentHashMap<>();
	}

	public void addSpot(ParkingSpot spot) {
		spots.put(spot.getSpotId(), spot);
	}

	public Optional<ParkingSpot> getSpot(Vehicle vehicle) {
		return spots.values().stream()
		.filter(spot -> 
			spot.isSpotAvailable() && spot.canParkVehicle(vehicle))
		.min(Comparator.comparing(ParkingSpot::getSpotId));
	}

	public void displayAvailableSpots() {
		Map<VehicleType, Long> availSpots = spots.values().stream()
		.filter(ParkingSpot::isSpotAvailable)
		.collect(Collectors.groupingBy(ParkingSpot::getSpotType, Collectors.counting()));

		for (var type : VehicleType.values()) {
            System.out.printf("  %s available spots: %d%n", type, availSpots.getOrDefault(type, 0L));
        }
	}
}