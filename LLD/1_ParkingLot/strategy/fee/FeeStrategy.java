package strategy.fee;

import parking.*;

public interface FeeStrategy {
	int calculateFee(ParkingTicket ticket);
}