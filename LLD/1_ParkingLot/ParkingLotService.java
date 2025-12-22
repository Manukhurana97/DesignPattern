import entity.*;
import parking.*;
import strategy.fee.*;
import strategy.parking.*;

import java.util.*;
import java.util.concurrent.*;

public class ParkingLotService {

	private static ParkingLotService parkingLotService;
	private ParkingStategy parkingStategy;
	private FeeStrategy feeStrategy;
	private final List<ParkingFloor> floors;
	private final Map<String, ParkingTicket> tickets ;

	public ParkingLotService() {
		this.parkingStategy = new NearestParking();
		this.feeStrategy = new FlatRate();
		this.floors = new ArrayList<>();
		this.tickets = new ConcurrentHashMap<>();
	}

	public static synchronized ParkingLotService getInstance() {
		if(parkingLotService == null) {
			parkingLotService = new ParkingLotService();
		}

		return parkingLotService;
	}

	public void changeParkingStrategy(ParkingStategy parkingStategy) {
		this.parkingStategy = parkingStategy;
	}

	private void changeFeeStrategy(FeeStrategy feeStrategy) {
		this.feeStrategy = feeStrategy;
	}

	public void addFloor(ParkingFloor floor) {
		floors.add(floor);
	} 

	public void showAvailability() {
		for(ParkingFloor floor: floors) {
			floor.displayAvailableSpots();
		}
	}

	public ParkingTicket entry(Vehicle vehicle) {
		Optional<ParkingSpot> spot = parkingStategy.findParkingSpot(floors, vehicle);
		if(spot.isPresent()) {
			spot.get().parkVehicle(vehicle);
			ParkingTicket ticket = new ParkingTicket(System.currentTimeMillis(), vehicle, spot.get());
			tickets.put(vehicle.getVehicleNumber(), ticket);

			System.out.printf("%s park at %s. Ticket %s\n", vehicle.getVehicleNumber(), spot.get().getSpotId(), ticket.getTickedtId());
			return ticket;
		}
		System.out.print("Parking lot is full \n");
		return null;
	}

	public int exit(Vehicle vehicle) {
		if(!tickets.containsKey(vehicle.getVehicleNumber())) {
			System.out.print("Mp record found");
			return 0;
		}

		ParkingTicket ticket = tickets.get(vehicle.getVehicleNumber());
		ticket.getSpot().unparkVehicle();
		tickets.remove(vehicle.getVehicleNumber());
		return feeStrategy.calculateFee(ticket);
	}
}