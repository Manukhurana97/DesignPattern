package Parking;

import vehicle.Vehicle;

import java.util.UUID;

public class ParkingTicket {
    private final String ticketNumber;
    private final long entryTime;
    private long exitTime;
    private final Vehicle vehicle;
    private final ParkingSpot spot;

    public ParkingTicket(long entryTime, Vehicle vehicle, ParkingSpot spot) {
        this.ticketNumber = UUID.randomUUID().toString();
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.spot = spot;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setExitTime(){
        this.exitTime = System.currentTimeMillis();
    }
}
