package strategy.driverMatching;

import entities.*;
import java.util.*;

public interface DriverMatchingStrategy {
    Optional<Driver> searchDriver(List<Driver> drivers);
}
