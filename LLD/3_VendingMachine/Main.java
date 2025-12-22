import enums.*;
import service.*;


public class Main {
	public static void main(String[] args) {
		VendingMachineService machine  = VendingMachineService.getInstance();
	

		machine.addItem("A1", "COKE", 10, 11);
		machine.addItem("A2", "PEPSI", 10, 10);
		machine.addItem("A3", "LIMCA", 10, 13);
		machine.addItem("A4", "FANTA", 10, 10);
		machine.addItem("A5", "7UP", 10, 10);

		System.out.println();
 

		System.out.println("--- Step1: Select Item ---");
		machine.selectItem("A3");

		System.out.println("--- Step2: Insert Coin ---");
		machine.insertCoin(Coin.TEN);
		machine.insertCoin(Coin.TWO);
		machine.insertCoin(Coin.ONE);
	

		System.out.println("--- Step3: Dispance Item ---");
		machine.dispense();

		machine.refund();

		System.out.println("_".repeat(100)+"\n");

		System.out.println("--- Step1: Select Item ---");
		machine.selectItem("A1");

		System.out.println("--- Step2: Insert Coin ---");
		machine.insertCoin(Coin.TEN);
		machine.insertCoin(Coin.ONE);
	

		System.out.println("--- Step3: Dispance Item ---");
		machine.refund();
	}
}