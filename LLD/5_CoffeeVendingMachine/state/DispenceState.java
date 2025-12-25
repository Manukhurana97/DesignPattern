package state;

import factory.Coffee;
import enums.*;
import service.*;

import java.util.Map;


public class DispenceState implements VendingMachineState {

	public void selectCoffee(VendingMachine machine, Coffee coffee, Map<Ingredient, Integer> extras) {
		System.out.println(" -> Coffee Already selected, to Select new please cancel this one");

	}

	public void insertMoney(VendingMachine machine, Note note) {
		System.out.println(" -> Amount already paid");
	}
	
	public void dispanceCoffee(VendingMachine machine) {
		System.out.println(" -> dispensing ...");
		machine.makeCoffeeHelper();
		System.out.println(" -> enjoy your coffee \n\n");
		machine.setState(new SelectCoffeeState());
	}
	
	public void cancel(VendingMachine machine) {
		machine.cancelWithRefund();
	}

}