package state;

import enums.Coin;
import vendingMachine.VendingMachine;

public class DispatchProduct  extends VendingMachineState {

	public DispatchProduct(VendingMachine vendingMachine) {
		super(vendingMachine);	
	}


	@Override
	public void selectItem(String id){
		System.out.println("Item already selected");
	}

	@Override
	public void insertCoin(Coin coin) {
		System.out.println("coins already inserted");
	}

	@Override
	public void dispatchProduct() {
        System.out.println("Dispatching product");
		vendingMachine.nextStep(new Idle(vendingMachine));
	}

	@Override
	public void refund() {
		System.out.println("dispatching in progress, Refund not allowed");
	}
}