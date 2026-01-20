package service;

import entities.Driver;
import entities.Rider;
import entities.Trip;
import enums.TripStatus;
import strategy.pricing.PricingStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TripManager {
    public static TripManager INSTANCE;
    private final DriverManager driverManager;
    private final RiderManager riderManager;
    private final Map<String, Trip> trips = new ConcurrentHashMap();

    public TripManager() {
        this.driverManager = DriverManager.getInstance();
        this.riderManager = RiderManager.getInstance();
    }

    public static synchronized TripManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TripManager();
        }

        return INSTANCE;
    }

    public Trip requestTrip(Rider rider, String startLocation, String endLocation) {
        Trip trip = new Trip(rider, startLocation, endLocation);
        return this.requestTripHelper(trip);
    }

    public Trip requestTrip(Rider rider, String startLocation, String endLocation, PricingStrategy pricingStrategy) {
        Trip trip = new Trip(rider, startLocation, endLocation, pricingStrategy);
        return this.requestTripHelper(trip);
    }

    private Trip requestTripHelper(Trip trip) {
        if (!assignDriver(trip)) {
            System.out.println("No Driver found, please try again after some time");
            trips.put(trip.getId(), trip);
            return trip;
        }
        calcualatePrice(trip);
        trips.put(trip.getId(), trip);
        System.out.printf("%s assigned and fare price %s \n", trip.getDriver().getName(), trip.getPrice());
        return trip;
    }

    private boolean assignDriver(Trip trip) {
        Optional<Driver> driver = trip.getDriverMatchingStrategy().searchDriver(driverManager.getDrivers());
        if (driver.isEmpty()) {
            trip.setStatus(TripStatus.NO_DRIVER_FOUND);
            return false;
        }
        this.driverManager.markDriverBusy(driver.get());
        trip.assignDriver(driver.get());
        return true;
    }

    private void calcualatePrice(Trip trip) {
        trip.setPrice(trip.getPricingStrategy().calculatePrice(trip));
    }

    public void startTrip(Trip trip) {
        trip.startTrip();
        System.out.printf("--- Trip Started %s \n---", trip.getStartTime());
    }

    public void endTrip(Trip trip) {
        trip.endTrip();
        this.driverManager.markDriverAvailable(trip.getDriver());
        System.out.printf("--- Trip ended at %s , Please pay %s --- \n", trip.getEndTime(), trip.getPrice());
    }
}
