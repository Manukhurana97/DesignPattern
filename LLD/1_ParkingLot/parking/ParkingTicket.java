package parking;

import entity.*;
import parking.*;

import java.util.*; 

public class ParkingTicket {
	private final String ticketId;
	private final Vehicle vehicle;
	private final ParkingSpot spot;
	private final long entryTime;
	private long exitTime;


	public ParkingTicket(long entryTime, Vehicle vehicle, ParkingSpot spot) {
		this.ticketId = UUID.randomUUID().toString();
		this.entryTime = entryTime;
		this.vehicle = vehicle;
		this.spot = spot;
	}

	public String getTickedtId() { return ticketId; }
	public long getEntryTime() { return entryTime; }
	public Vehicle getVehicle() { return vehicle; }
	public ParkingSpot getSpot() { return spot; }

}