package service;

import entities.Driver;
import enums.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    public static DriverManager INSTANCE;
    private final Map<String, Driver> drivers = new ConcurrentHashMap<>();

    public static synchronized DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }

        return INSTANCE;
    }

    public List<Driver> getDrivers() {
        return new ArrayList<>(drivers.values());
    }

    public Driver registerDriver(String name, boolean isAvailable, Rating rating) {
        Driver driver = new Driver(name, isAvailable, rating);
        drivers.put(driver.getId(), driver);
        System.out.println("--- Driver added ---");
        return driver;
    }

    public void markDriverBusy(Driver driver) {
        driver.setAvailability(false);
    }

    public void markDriverAvailable(Driver driver) {
        driver.setAvailability(true);
    }
}