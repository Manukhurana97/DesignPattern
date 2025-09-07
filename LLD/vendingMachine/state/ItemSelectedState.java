package state;

import enums.Coin;
import vendingMachine.VendingMachine;

public class ItemSelectedState extends VendingMachineState {

	public ItemSelectedState(VendingMachine vendingMachine) {
		super(vendingMachine);	
	}
	

	@Override
	public void selectItem(String id) {
		System.out.println("Item already selected");
	}

	@Override
	public void insertCoin(Coin coin){
		vendingMachine.addBalance(coin.getValue());
		System.out.println("Coin inserted "+ coin.getValue());

		int price = vendingMachine.getSelectProduct().getPrice();

		if(vendingMachine.getBalance() >= price) {
			System.out.println("sufficient money receive");
			vendingMachine.nextStep(new HasMoneyState(vendingMachine));
		}

	}

	@Override
	public void dispatchProduct(){
		System.out.println("please insert sufficient balance");
	}
	
	@Override
	public void refund(){
		vendingMachine.reset();
		vendingMachine.nextStep(new Idle(vendingMachine));
	}
}