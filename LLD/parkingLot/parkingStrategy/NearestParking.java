package parkingStrategy;

import Parking.ParkingFloor;
import Parking.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestParking implements ParkingStrategy {

    @Override
    public Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            var spot = floor.getSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }

        return Optional.empty();
    }
}
