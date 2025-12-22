package strategy.fee;

import parking.*;

public class FlatRate implements FeeStrategy {

	public int calculateFee(ParkingTicket ticket) {
		long duration = System.currentTimeMillis() - ticket.getEntryTime();
		int hours = (int) (duration / 1000*60*60) + 1;

		return hours * ticket.getVehicle().getVehicleType().getFee();
	}
}