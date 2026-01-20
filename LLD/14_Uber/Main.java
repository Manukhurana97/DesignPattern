import service.*;
import entities.*;
import enums.*;
import strategy.pricing.RatingBasedPriceStrategy;

public class Main {
	public static void main(String[] args) {
		DriverManager driverManager = DriverManager.getInstance();
		driverManager.registerDriver("Driver_1", true, Rating.FIVE);
		driverManager.registerDriver("Driver_2", false, Rating.FIVE);
		driverManager.registerDriver("Driver_3", true, Rating.FIVE);

		RiderManager riderManager = RiderManager.getInstance();
		Rider rider1 = riderManager.registerRider("rider_1", "123123123");
		Rider rider2 = riderManager.registerRider("rider_1", "123123123");

		TripManager tripManager = TripManager.getInstance();
		Trip trip1 = tripManager.requestTrip(rider1, "DEL",  "HYD");
		tripManager.startTrip(trip1);


		Trip trip2 = tripManager.requestTrip(rider2, "DEL",  "HYD", new RatingBasedPriceStrategy());
		tripManager.startTrip(trip2);
		tripManager.endTrip(trip2);

		tripManager.endTrip(trip1);
	}
}