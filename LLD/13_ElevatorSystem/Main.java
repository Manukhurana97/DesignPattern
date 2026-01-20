import enums.*;
import service.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		int noOfElevators = 3;
		ElevatorService service = ElevatorService.getInstance(noOfElevators);

		service.start();
		System.out.println("Elevator system started, ConsoleDisplay is observing.\n");

		service.requestElevator(5, Direction.UP);
		Thread.sleep(100);

		service.selectFloor(1, 10);
		Thread.sleep(200);

		service.requestElevator(3, Direction.DOWN);
		Thread.sleep(300);
		service.selectFloor(3, 1);

		Thread.sleep(1000);
	
		service.shutdown();
	}
}