import Parking.ParkingFloor;
import Parking.ParkingSpot;
import Parking.ParkingTicket;
import feeStrategy.FeeStrategy;
import feeStrategy.FlatRateStrategy;
import parkingStrategy.BestFit;
import parkingStrategy.ParkingStrategy;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> parkingFloors;
    private Map<String, ParkingTicket> parkingTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;

    ParkingLot() {
        parkingFloors = new ArrayList<>();
        parkingTickets = new ConcurrentHashMap<>();
        feeStrategy = new FlatRateStrategy();
        parkingStrategy = new BestFit();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }

        return instance;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addFloor(ParkingFloor parkingFloor) {
        parkingFloors.add(parkingFloor);
    }

    public ParkingTicket entry(Vehicle vehicle) {
        Optional<ParkingSpot> spot = parkingStrategy.findParkingSpot(parkingFloors, vehicle);
        if (spot.isPresent()) {
            spot.get().parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(System.currentTimeMillis(), vehicle, spot.get());
            parkingTickets.put(vehicle.getVehicleNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getVehicleNumber(), spot.get().getSpotNumber(), ticket.getTicketNumber());
            return ticket;
        }

        System.out.println("Parking lot is full");
        return null;
    }

    public double exit(Vehicle vehicle) {
        if (!parkingTickets.containsKey(vehicle.getVehicleNumber())) {
            System.out.println("Invalid Vehicle Number, No record found");
            return 0;
        }

        ParkingTicket ticket = parkingTickets.get(vehicle.getVehicleNumber());
        ticket.getSpot().unParkVehicle(vehicle);
        parkingTickets.remove(vehicle.getVehicleNumber());

        System.out.println(vehicle.getVehicleNumber() +" exited");
        return feeStrategy.calculateFee(ticket);
    }


    public void showAvailability() {
        for (ParkingFloor floor : parkingFloors) {
            floor.displayAvailability();
        }
    }
}
