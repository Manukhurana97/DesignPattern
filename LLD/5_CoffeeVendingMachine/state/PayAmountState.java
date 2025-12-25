package state;

import factory.Coffee;
import enums.*;
import service.*;

import java.util.Map;


public class PayAmountState implements VendingMachineState {
	public void selectCoffee(VendingMachine machine, Coffee coffee, Map<Ingredient, Integer> extras) {
		System.out.println(" -> Coffee Already selected, to Select new please cancel this one");
	}

	public void insertMoney(VendingMachine machine, Note note) {

		machine.insertNoteHelper(note);

		if (machine.getRemainingAmountHelper() == 0) {
			machine.setState(new DispenceState());
			System.out.println(" -> Ready to dispense");
			return;
		} else if (machine.getRemainingAmountHelper() < 0) {
			// logic to calculate currency note to refund, out of scope
		}
	}

	public void dispanceCoffee(VendingMachine machine) {
		System.out.println(" -> Pay the amount first");
	}

	public void cancel(VendingMachine machine) {
		machine.cancelWithRefund();
	}

}
