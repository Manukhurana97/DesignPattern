import vehicle.*;
import Parking.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = ParkingLot.getInstance();

        // --- Setup parking lot ---
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1S1", VehicleType.SMALL));
        floor1.addSpot(new ParkingSpot("F1M1", VehicleType.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1L1", VehicleType.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2S1", VehicleType.SMALL));
        floor2.addSpot(new ParkingSpot("F2M1", VehicleType.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2L1", VehicleType.LARGE));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        // --- Vehicles ---
        Vehicle car1   = new Car("CAR-123");
        Vehicle car2   = new Car("CAR-789");
        Vehicle bike1  = new Bike("BIKE-111");
        Vehicle truck1 = new Truck("TRUCK-99");
        Vehicle truck2 = new Truck("TRUCK-55");

        // --- Scenario ---
        System.out.println("\n=== Initial Availability ===");
        parkingLot.showAvailability();

        System.out.println("\n=== Vehicle Entries ===");
        parkingLot.entry(car1);   // enter car
        parkingLot.entry(car2);   // enter another car
        parkingLot.entry(bike1);  // enter bike
        parkingLot.entry(truck1); // enter truck

        System.out.println("\n=== Availability After Parking ===");
        parkingLot.showAvailability();

        // Wait 2 sec to simulate time for fee
        Thread.sleep(2000);

        System.out.println("\n=== Vehicle Exits ===");
        double fee = parkingLot.exit(truck1); // exit truck
        System.out.printf("Truck TRUCK-99 exited. Fee: $%.2f%n", fee);

        System.out.println("\n=== Availability After Truck Exit ===");
        parkingLot.showAvailability();

        System.out.println("\n=== New Truck Entry ===");
        parkingLot.entry(truck2); // enter another truck

        System.out.println("\n=== Final Availability ===");
        parkingLot.showAvailability();
    }
}
