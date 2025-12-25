package state;

import enums.*;
import factory.Coffee;
import service.*;

import java.util.Map;

public class SelectCoffeeState implements VendingMachineState {

	public void selectCoffee(VendingMachine machine, Coffee coffee, Map<Ingredient, Integer> extras) {
		machine.selectCoffeeHelper(coffee, extras);
		machine.setState(new PayAmountState());
	}


	public void insertMoney(VendingMachine machine, Note note) {
		System.out.println(" -> select the coffee first");
	}


	public void dispanceCoffee(VendingMachine machine) {
		System.out.println(" -> select the coffee first");
	}


	public void cancel(VendingMachine machine) {
		machine.cancelCoffee();
	}
}