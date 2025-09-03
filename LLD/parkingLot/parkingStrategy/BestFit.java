package parkingStrategy;

import Parking.ParkingFloor;
import Parking.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFit implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        Optional<ParkingSpot> bestSpot = Optional.empty();
        for (ParkingFloor floor : parkingFloors) {
            Optional<ParkingSpot> spot = floor.getSpot(vehicle);
            if (spot.isPresent()) {
                if (bestSpot.isEmpty()) {
                    bestSpot = spot;
                } else {
                    if (spot.get().getSpotType().getSize() < bestSpot.get().getSpotType().getSize()) {
                        bestSpot = spot;
                    }
                }
            }
        }
        return bestSpot;
    }
}
