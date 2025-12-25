package state;

import enums.*;
import factory.Coffee;
import service.*;

import java.util.Map;

public interface VendingMachineState {
	void selectCoffee(VendingMachine machine, Coffee coffee, Map<Ingredient, Integer> extras);
	void insertMoney(VendingMachine machine, Note note);
	void dispanceCoffee(VendingMachine machine);
	void cancel(VendingMachine machine);
}