package service;

import java.util.*;
import enums.*;

public class Inventory {
	private static Inventory INSTANCE;
	private Map<Ingredient, Integer> inventory = new HashMap<>();

	public Inventory() {}

	public static synchronized Inventory getInstance() { 
		if(INSTANCE == null) {
			INSTANCE = new Inventory();
		}

		return INSTANCE; 
	}

	public void addStock(Ingredient ingredient, int quantity) {
		inventory.put(ingredient, inventory.getOrDefault(ingredient, 0) + quantity);
	}

	public boolean doWeHave(Ingredient ingredient, int quantity) {
		return (inventory.containsKey(ingredient) && inventory.get(ingredient) >= quantity);
	}

	public void consume(Ingredient ingredient, int quantity) {
		inventory.put(ingredient, inventory.get(ingredient) - quantity);
	}

	public void printInventory() {
		for(Map.Entry<Ingredient, Integer> k_v : inventory.entrySet()) {
			System.out.printf("%s quantity: %s\n",k_v.getKey(), k_v.getValue());
		}

		System.out.println("-".repeat(20));
	}
}