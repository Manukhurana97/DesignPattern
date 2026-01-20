package entities;

import java.time.LocalDateTime;
import java.util.UUID;

import enums.*;
import strategy.driverMatching.NearsetDriverMatchingStrategy;
import strategy.driverMatching.DriverMatchingStrategy;
import strategy.pricing.DefaultPriceStrategy;
import strategy.pricing.PricingStrategy;

public class Trip {
    private final String id;
    private Rider rider;
    private Driver driver;
    private String startLocation;
    private String endLocation;
    private Double price;
    private LocalDateTime requestTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TripStatus tripStatus;
    private PricingStrategy pricingStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;

    public Trip(Rider rider, String startLocation, String endLocation) {
        this.id = UUID.randomUUID().toString();
        this.tripStatus = TripStatus.INIT;
        this.rider = rider;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.requestTime = LocalDateTime.now();
        this.pricingStrategy = new DefaultPriceStrategy();
        this.driverMatchingStrategy = new NearsetDriverMatchingStrategy();
    }

    public Trip(Rider rider, String startLocation, String endLocation, PricingStrategy strategy) {
        this(rider, startLocation, endLocation);
        this.pricingStrategy = strategy;
    }

    public String getId() { return id; }

    public Driver getDriver() { return driver; }

    public double getPrice() { return price; }

    public LocalDateTime getStartTime() {return startTime;}

    public LocalDateTime getEndTime() { return endTime;}

    public PricingStrategy getPricingStrategy() { return pricingStrategy; }

    public DriverMatchingStrategy getDriverMatchingStrategy() { return driverMatchingStrategy; }

    public void assignDriver(Driver driver) {
        this.tripStatus = TripStatus.DRIVER_ASSIGNED;
        this.driver = driver;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public void startTrip() {
        this.startTime = LocalDateTime.now();
        this.setStatus(TripStatus.STARTED);
    }

    public void endTrip() {
        this.endTime = LocalDateTime.now();
        this.setStatus(TripStatus.COMPLETED);
    }
}
