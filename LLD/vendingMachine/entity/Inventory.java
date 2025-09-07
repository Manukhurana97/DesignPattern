package entity;

import java.util.*;

public class Inventory {
	private final Map<String, Product> products = new HashMap<>();
	private final Map<String, Integer> stockMap = new HashMap();

	public void addProduct(String id, Product product, int count) {
		products.put(id, product);
		stockMap.put(id, stockMap.getOrDefault(id, 0) + 1);
	}


	public Map<String, Integer> getStockMap() {
		return stockMap;
	}

	public Product getProduct(String id) {
		return products.get(id);
	}

	public boolean isAvailable(String id) {
		return stockMap.getOrDefault(id, 0) > 0;
	}

	public void reduceCount(String id) {
		stockMap.put(id, stockMap.get(id)-1);
	}
}