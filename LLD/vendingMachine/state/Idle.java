package state;

import enums.Coin;
import vendingMachine.VendingMachine;

public class Idle extends VendingMachineState {
	public Idle(VendingMachine vendingMachine) {
		super(vendingMachine);
	}


	@Override
	public void selectItem(String id){
		if(!vendingMachine.getInventory().isAvailable(id)) {
			System.out.println("Item not available");
			return;
		} 

		vendingMachine.selectedItem = id;
		vendingMachine.nextStep(new ItemSelectedState(vendingMachine));
		System.out.println("Item Selected "+ id);
	}

	@Override
	public void insertCoin(Coin coin) {
		System.out.println("select the item first");
	}

	@Override
	public void dispatchProduct() {
		System.out.println("select the item first");
	}

	@Override
	public void refund() {
		System.out.println("select the item first");
	}

}