package entity;

public class Product {
	private String code;
	private String name;
	private int price;
 
	public Product(String code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getName() { return name; }
	public int getPrice() { return price; }
}