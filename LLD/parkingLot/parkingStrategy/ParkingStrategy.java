package parkingStrategy;

import Parking.ParkingFloor;
import Parking.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
