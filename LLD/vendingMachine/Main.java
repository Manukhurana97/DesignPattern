import enums.Coin;
import vendingMachine.VendingMachine;

public class Main {
	public static void main(String[] args) {
		VendingMachine machine = VendingMachine.getInstance();
		
		// Add products to the inventory
        machine.addItem("A1", "Coke", 25, 3);
        machine.addItem("A2", "Pepsi", 25, 2);
        machine.addItem("B1", "Water", 10, 5);
		
		machine.selectItem("A1");
		machine.insertCoin(Coin.TEN);
		machine.insertCoin(Coin.TEN);
		machine.insertCoin(Coin.FIVE);
		machine.dispatchProduct();

	}
}