package state;

import enums.*;
import service.VendingMachineService;

public abstract class VendingMachineState {

	VendingMachineService service;

	public VendingMachineState(VendingMachineService service) {
		this.service = service;
	}

	public abstract void selectItem(String itemId);
	public abstract void insertCoin(Coin coin);
	public abstract void dispatch();
	public abstract void refund();
}