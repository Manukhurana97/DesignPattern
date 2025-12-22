package entities;

public class Item {
	private String id;
	private String name;
	private int amount;

	public Item(String id, String name, int amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
	}

	public String getId() { return id; }
	public String getName() { return name; }
	public int getAmount() { return amount; }
}