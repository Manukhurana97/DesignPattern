package service;

import java.util.*;

import entities.*;


public class Inventory {
	private static Inventory INSTANCE;
	private final Map<String, Item> items;
	private final Map<String, Integer> stocks;

	public Inventory() {
		items = new HashMap<>();
		stocks = new HashMap<>();
	}


	public synchronized static Inventory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Inventory();
		}

		return INSTANCE;
	}

	public void addItem(String itemId, int quantity, Item item) {
		items.put(itemId, item);
		stocks.put(itemId, stocks.getOrDefault(itemId, 0) + quantity);
	}

	public boolean isAvailable(String id) {
		return stocks.get(id) > 0;
	}

	public int getAmount(String itemId) {
		return items.get(itemId).getAmount();
	} 

}