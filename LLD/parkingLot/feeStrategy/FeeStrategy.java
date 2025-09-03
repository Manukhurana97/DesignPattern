package feeStrategy;

import Parking.ParkingTicket;
import vehicle.Vehicle;

public interface FeeStrategy {

    double calculateFee(ParkingTicket parkingTicket);
}
