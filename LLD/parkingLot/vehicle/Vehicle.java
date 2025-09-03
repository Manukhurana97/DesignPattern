package vehicle;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType type;

    Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getType() {
        return type;
    }
}