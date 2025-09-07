package state;

import enums.Coin;
import vendingMachine.VendingMachine;


public abstract class VendingMachineState {
	VendingMachine vendingMachine;

	public VendingMachineState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public abstract void selectItem(String id);
	public abstract void insertCoin(Coin coin);
	public abstract void dispatchProduct();
	public abstract void refund();
}