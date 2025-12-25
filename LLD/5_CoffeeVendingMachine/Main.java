import enums.*;
import service.*;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		VendingMachine machine = VendingMachine.getInstance();
		Inventory inventory = Inventory.getInstance();

		System.out.println(" == Initializing Vending Machine ==");

		inventory.addStock(Ingredient.WATER, 100);
		inventory.addStock(Ingredient.COFFEE_BEANS, 100);
		inventory.addStock(Ingredient.MILK, 100);
		inventory.addStock(Ingredient.SUGER, 100);
		inventory.addStock(Ingredient.CARAMEL_SYRUP, 100);
		inventory.addStock(Ingredient.WIPPING_CREAM, 100);

		inventory.printInventory();

		System.out.print("\n=== selecting latte ===\n");
		if(machine.selectCoffee(CoffeeType.LATTE, Map.of())){
			machine.insertMoney(Note.TWO_HUNDRED);
			machine.insertMoney(Note.FIFTY);
			machine.dispenceCoffee();

			inventory.printInventory();
		} else {
			System.out.println("Out of Stock");
		}

		System.out.print("\n=== selecting latte ===\n");
		if(machine.selectCoffee(CoffeeType.LATTE, Map.of(Ingredient.COFFEE_BEANS, 40))){
			machine.insertMoney(Note.TWO_HUNDRED);
			machine.insertMoney(Note.FIFTY);
			machine.dispenceCoffee();

			inventory.printInventory();
		} else {
			System.out.println("Out of Stock");
		}

		System.out.print("\n=== selecting latte ===\n");
		if(machine.selectCoffee(CoffeeType.LATTE, Map.of(Ingredient.COFFEE_BEANS, 50))){
			machine.insertMoney(Note.TWO_HUNDRED);
			machine.insertMoney(Note.FIFTY);
			machine.dispenceCoffee();

			inventory.printInventory();
		} else {
			System.out.println("Out of Stock");
		}

		System.out.print("\n=== selecting Cappuccino ===\n");
		if(machine.selectCoffee(CoffeeType.CAPPUCCINO, Map.of(Ingredient.CARAMEL_SYRUP, 2, Ingredient.WIPPING_CREAM, 2))) {
			machine.insertMoney(Note.TWO_HUNDRED);
			machine.insertMoney(Note.FIFTY);
			machine.dispenceCoffee();

			inventory.printInventory();
		} else {
			System.out.println("Out of Stock");
		}
	}
}
