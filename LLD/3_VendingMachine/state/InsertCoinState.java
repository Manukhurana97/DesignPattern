package state;

import service.*;
import enums.*;

public class InsertCoinState extends VendingMachineState {

	public InsertCoinState(VendingMachineService service) {
		super(service);
	}


	public void selectItem(String itemId) {
		System.out.println("Item already Selected");
		return;
	}


	public void insertCoin(Coin coin) {
		System.out.println("Amount Already paid");
		return;
	}


	public void dispatch() {
		service.setState(new IdleState(service));
		service.reset();
		return;
	}


	public void refund() {

		System.out.printf("Refunding amount %s ...\n", service.getInventory().getAmount(service.getSelected()));
		service.setState(new IdleState(service));
		service.reset();
		return;
	}
}