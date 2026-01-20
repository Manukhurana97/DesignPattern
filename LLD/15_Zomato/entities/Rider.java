package entities;

import java.util.UUID;

public class Rider {
    private final String id;
    private String name;
    private String vehicleNumber;
    private String phoneNumber;
    private boolean isAvailable;

    public Rider(String name, String vehicleNumber, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.phoneNumber = phoneNumber;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
