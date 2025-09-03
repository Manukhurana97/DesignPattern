package feeStrategy;


import Parking.ParkingTicket;

public class FlatRateStrategy implements FeeStrategy {


    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = System.currentTimeMillis() - parkingTicket.getEntryTime();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * parkingTicket.getVehicle().getType().getFee();
    }
}
