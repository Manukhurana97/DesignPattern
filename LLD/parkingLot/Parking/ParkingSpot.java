package Parking;

import vehicle.Vehicle;
import vehicle.VehicleType;

public class ParkingSpot {
    private String spotNumber;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private final VehicleType spotType;


    public ParkingSpot(String spotNumber, VehicleType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public boolean isSpotAvailable() {
        return !isOccupied;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }


    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unParkVehicle(Vehicle vehicle) {
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public boolean canParkCar(Vehicle vehicle) {
        return vehicle.getType().getSize() >= spotType.getSize();
    }
}
