package state;

import service.*;
import enums.*;


public class IdleState extends VendingMachineState {

	public IdleState(VendingMachineService service) {
		super(service);
	}


	public void selectItem(String itemId) {
		if(!service.getInventory().isAvailable(itemId)) {
			System.out.println("Item out of stock");
			return;
		}


		service.setSelectItem(itemId);
		service.setState(new ItemSelectedState(service));
		System.out.println("Item selected: "+ itemId);
	}


	public void insertCoin(Coin coin) {
		System.out.println("Select item first");
		return;
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