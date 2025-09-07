package state;

import enums.Coin;
import vendingMachine.VendingMachine;

public class HasMoneyState extends VendingMachineState {

	public HasMoneyState(VendingMachine vendingMachine) {
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
		vendingMachine.nextStep(new DispatchProduct(vendingMachine));
		vendingMachine.dispatchProduct();
	}

	@Override
	public void refund() {
		vendingMachine.refundBalance();
		vendingMachine.reset();
		vendingMachine.nextStep(new Idle(vendingMachine));
	}
}