package parking;

import entity.Vehicle;
import entity.VehicleType;

import static entity.VehicleType.TRUCK;
import static entity.VehicleType.CAR;
import static entity.VehicleType.BIKE;

public class ParkingSpot {
	private final String spotId;
	private final VehicleType vehicleType;
	private boolean available;
	private Vehicle vehicle;
	
	public ParkingSpot(String spotId, VehicleType vehicleType) {
		this.spotId = spotId;
		this.vehicleType = vehicleType;
		this.available = true;
	}

	public String getSpotId() { return spotId; }
	public boolean isSpotAvailable() { return available; }  
	public VehicleType getSpotType() { return vehicleType; }

	public void parkVehicle(Vehicle vehicle) {
		this.available = false;
		this.vehicle = vehicle;
	}

	public void unparkVehicle() {
		this.available = true;
		this.vehicle = null;
	}

	public boolean canParkVehicle(Vehicle vehicle) {
		return switch(vehicle.getVehicleType()) {
			case TRUCK -> this.vehicleType.getSize() >= 3;
			case CAR -> this.vehicleType.getSize() >= 2;
			case BIKE ->  this.vehicleType.getSize() == 1;
            default -> false;
		};
	}	

}