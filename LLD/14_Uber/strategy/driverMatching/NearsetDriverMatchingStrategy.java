package strategy.driverMatching;

import entities.Driver;

import java.util.List;
import java.util.Optional;

public class NearsetDriverMatchingStrategy implements DriverMatchingStrategy {
    public Optional<Driver> searchDriver(List<Driver> drivers) {
        return drivers.stream().filter(Driver::isAvailable).findFirst();
    }
}
