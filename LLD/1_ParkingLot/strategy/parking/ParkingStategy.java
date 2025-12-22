package strategy.parking;

import java.util.*;

import entity.*;
import parking.*;


public interface ParkingStategy {
	public Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> floors, Vehicle vehicle);
}