
import java.util.*;
import java.util.concurrent.*;
import java.time.*;

enum SeatStatus {AVAILABLE, RESERVED, BOOKED};
enum SeatType {ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST};
enum BookingStates {CONFIRMED, CANCELLED, PENDING, EXPIRED};
enum FlightStatus {ON_TIME, DELAYED, CANCELLED};

class Aeroplane {
	private final String model;
	private final int totalSeat;
	private final String tailNumber;
	

	public Aeroplane(String model, String tailNumber, int totalSeat) {
		this.model = model;
		this.tailNumber = tailNumber;
		this.totalSeat = totalSeat;
	}
}

class Flight {
	private final String id;
	private final String flightNumber;
	private final String src;
	private final String dst;
	private final LocalDateTime departureTime;
	private final LocalDateTime arivalTime;
	private FlightStatus flightStatus;
	private Map<String, Seat> seat;
	private List<Seat> availableSeats;
	private Aeroplane aeroplace;


	public Flight(String flightNumber, String src, String dst, LocalDateTime departureTime, LocalDateTime arrivalTime, Aeroplane aeroplace) {
		this.id = UUID.randomUUID().toString();
		this.flightNumber = flightNumber;
		this.src = src;
		this.dst = dst;
		this.departureTime = departureTime;
		this.arivalTime = arrivalTime;
		this.flightStatus = FlightStatus.ON_TIME;
		this.seat = new ConcurrentHashMap<>();
		availableSeats = new CopyOnWriteArrayList<>();
		this.aeroplace = aeroplace;
	}

	public boolean isSeatAvailable(String seatNo) {
		return seat.containsKey(seatNo) && !seat.get(seatNo).isBooked();
	}

	public void reserveSeat(String seatNo) {
		if(!seat.containsKey(seatNo)) {
			throw new IllegalArgumentException("Invalid Sear Number");
		}
		seat.get(seatNo).reserve();
	}

	public void releaseSeat(String seatNo) {
		if(!seat.containsKey(seatNo)) {
			throw new IllegalArgumentException("Invalid Seat Number");
		}
		seat.get(seatNo).release();
	}

	public String getId() {
		return id;
	}
}


class Seat {
	private final String seatNumber;
	private final SeatType seatType;
	private SeatStatus seatStatus;

	public Seat(String seatNumber, SeatType seatType) {
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.seatStatus = SeatStatus.AVAILABLE;
	}

	public boolean isBooked() { return seatStatus == SeatStatus.BOOKED; }

	public void reserve() {
		this.seatStatus = SeatStatus.RESERVED;
	}

	public void release() {
		this.seatStatus = SeatStatus.AVAILABLE;
	}

	public synchronized void booked() {
		this.seatStatus = SeatStatus.BOOKED;
	}
}	

class User {
	private final String passengerId;
	private final String name;
	private final String email;

	public User(String name, String email) {
		this.passengerId = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
	}


}

class Booking {
	private final String id;
	private final LocalDateTime bookedAt;
	private final Flight flight;
	private final User passenger;
	private final Seat seat;
	private final Double amount;
	private BookingStates status;

	public Booking(Flight flight, User passenger,  Seat seat, Double amount) {
		this.id = UUID.randomUUID().toString();
		this.bookedAt = LocalDateTime.now();
		this.flight = flight;
		this.passenger = passenger;
		this.seat = seat;
		this.amount = amount;
		this.status = BookingStates.CONFIRMED;
		this.seat.booked();
	}

	public void cancel() {
		this.status = BookingStates.CANCELLED;
		seat.release();
	}

	public String getId() {
		return id;
	}
}


class AeroplaneManager {
	public static AeroplaneManager INSTANCE;
	private final Map<String, Aeroplane> aeroplanes = new ConcurrentHashMap<>();


	public synchronized static AeroplaneManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AeroplaneManager();
		}

		return INSTANCE;
	}

	public Aeroplane addAeroplane(String model, int seats, String tailNumber) {
		Aeroplane aeroplane = new Aeroplane(model, tailNumber, seats);
		aeroplanes.put(tailNumber, aeroplane);
		return aeroplane;
	}
}


class UserManager {
	public static UserManager INSTANCE;
	private final Map<String, User> users = new ConcurrentHashMap<>();

	public synchronized static UserManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new UserManager();
		}

		return INSTANCE;
	}

	public User addUser(String name, String email) {
		User user = new User(name, email);
		users.put(email, user);
		return user;
	}
}

class FlightManager {
	public static FlightManager INSTANCE;
	private final Map<String, Flight> flights = new ConcurrentHashMap<>();

	public synchronized static FlightManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new FlightManager();
		}

		return INSTANCE;
	}

	public Flight setUpFlight(String flightNumber, String source, String destination, LocalDateTime departedTime, LocalDateTime arrivalTime, Aeroplane aeroplane) {
		Flight flight = new Flight(flightNumber, source, destination, departedTime, arrivalTime, aeroplane );
		flights.put(flight.getId(), flight);
		return flight;
	}
}


class BookingManager {
	public static BookingManager INSTANCE;
	public Map<String, Booking> bookings = new ConcurrentHashMap<>();

	public synchronized static BookingManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new BookingManager();
		}

		return INSTANCE;
	}

	public Booking bookSeat(Flight flight, User passenger, Seat seat, double amount) {
		if(seat.isBooked() ) {
			throw new RuntimeException("Seat already booked");
		}
		Booking booking = new Booking(flight, passenger, seat, amount);
		bookings.put(booking.getId(), booking);
		return booking;
	}

	public void cancelBooking(Booking booking) {
		booking.cancel();
	}
}

// Facade 
class AirlineBookingManager {
	public static AirlineBookingManager INSTANCE;
	public final AeroplaneManager aeroplaneManager;
	public final FlightManager flightManager;
	public final UserManager userManager;
	public final BookingManager bookingManager;

	public AirlineBookingManager() {
		this.aeroplaneManager = AeroplaneManager.getInstance();
		this.flightManager = FlightManager.getInstance();
		this.userManager = UserManager.getInstance();
		this.bookingManager = BookingManager.getInstance();
	}

	public synchronized static AirlineBookingManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AirlineBookingManager();
		}

		return INSTANCE;
	}

	public synchronized Aeroplane addAeroplane(String model, String tailNumber, int seats) {
		return this.aeroplaneManager.addAeroplane(model, seats, tailNumber);
	}
	public synchronized  Flight createFlight(String flightNumber, String source, String destination, LocalDateTime departTime, LocalDateTime arrivalTime, Aeroplane aeroplane) {
		return this.flightManager.setUpFlight(flightNumber, source, destination, departTime, arrivalTime, aeroplane);
	}

	public synchronized User addUser(String name, String email) {
		return this.userManager.addUser(name, email);
	}

	public synchronized Booking bookingTicket(Flight flight, User passenger, Seat seat) {
		return bookingManager.bookSeat(flight, passenger, seat, 6555);
	}
}

public class Main {
	public static void main(String[] args) {
		AirlineBookingManager airlineBookingManager = AirlineBookingManager.getInstance();

		Aeroplane aeroplane = airlineBookingManager.addAeroplane("A330", "AL-112", 330);
		Flight flight = airlineBookingManager.createFlight("6e-001", "DEL", "PNQ", LocalDateTime.now(), LocalDateTime.now(), aeroplane);
		User user = airlineBookingManager.addUser("User_1", "user@gmail.com");
		Booking booking = airlineBookingManager.bookingTicket(flight, user, new Seat("1", SeatType.BUSINESS));
	}
}