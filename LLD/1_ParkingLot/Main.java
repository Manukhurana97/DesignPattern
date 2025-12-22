import entity.*;
import parking.*;

public class Main {
    public static void main(String[] args) {

        ParkingLotService service = ParkingLotService.getInstance();

        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("A1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("A2", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("A3", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("A4", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("A5", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("A6", VehicleType.TRUCK));


        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("B1", VehicleType.BIKE));
        floor2.addSpot(new ParkingSpot("B2", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("B3", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("B4", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("B5", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("B6", VehicleType.TRUCK));
    
        service.addFloor(floor1);
        service.addFloor(floor2);


        Vehicle v1 = new Bike("B1");
        Vehicle v2 = new Bike("B2");
        Vehicle v3 = new Bike("B3");
        Vehicle v4 = new Bike("B4");
        Vehicle v5 = new Car("C1");
        Vehicle v6 = new Bike("B5");

        service.showAvailability();

        service.entry(v1);
        service.entry(v2);
        service.entry(v3);
        service.entry(v4);
        service.entry(v5);
        service.entry(v6);

        service.showAvailability();

        System.out.println("Undue amount: "+service.exit(v1));
        System.out.println("Undue amount: "+service.exit(v5));

        service.showAvailability();
    }
}