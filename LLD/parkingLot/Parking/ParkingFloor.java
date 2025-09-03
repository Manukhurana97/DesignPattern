package Parking;

import vehicle.Vehicle;
import vehicle.VehicleType;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingFloor {
    private int floorNumber;
    private final Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        spots = new ConcurrentHashMap<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Map<String, ParkingSpot> getSpots() {
        return spots;
    }

    public synchronized void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotNumber(), spot);
    }

    public Optional<ParkingSpot> getSpot(Vehicle vehicle) {
        return spots.values().stream()
                .filter(spot -> spot.isSpotAvailable() && spot.canParkCar(vehicle))
                .min(Comparator.comparing(ParkingSpot::getSpotNumber));
    }



    public void displayAvailability() {
        System.out.printf("--- Floor %d Availability ---%n", floorNumber);
        Map<VehicleType, Long> availableSpots = spots.values().stream()
                .filter(ParkingSpot::isSpotAvailable)
                .collect(Collectors.groupingBy(ParkingSpot::getSpotType, Collectors.counting()));

        for (var type : VehicleType.values()) {
            System.out.printf("  %s available spots: %d%n", type, availableSpots.getOrDefault(type, 0L));
        }
    }

}
