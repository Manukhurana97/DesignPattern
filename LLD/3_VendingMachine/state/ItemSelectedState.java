package state;

import service.*;
import enums.*;

public class ItemSelectedState extends VendingMachineState {

	public ItemSelectedState(VendingMachineService service) {
		super(service);
	}


	public void selectItem(String itemId) {
		System.out.println("Item already selected");
	}


	public void insertCoin(Coin coin) {
		if(service.getBalance() - coin.getAmount() == 0) {
			service.setState(new InsertCoinState(service));
			System.out.println("Ready to dispatch");
			return ;
		}

		int remaining = service.getBalance() - coin.getAmount();
		service.updateBalance(remaining);
		System.out.println("Remaining amount " + remaining);
	}


	public void dispatch() {
		System.out.println("Select item first");
		return;
	}


	public void refund() {
		System.out.println("Select item first");
		return;
	}
}