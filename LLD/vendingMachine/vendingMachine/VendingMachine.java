package vendingMachine;

import entity.*;

import enums.Coin;
import state.*;

public class VendingMachine {
	private static final VendingMachine instance = new VendingMachine();;
	private final Inventory inventory = new Inventory();
	private VendingMachineState vendingMachineState;
	public String selectedItem;
	public int balance;


	VendingMachine() {
		this.vendingMachineState = new Idle(this);
	}


	public static synchronized VendingMachine getInstance() {
		return instance;
	}

	public Product addItem(String id, String name, int price, int count) {
		Product product = new Product(id, name, price);
		inventory.addProduct(id, product, count);
		System.out.println(name +" added");
		return product;
	}

	public void selectItem(String id) {
		if(!inventory.isAvailable(id)) {
			System.out.println("Product out of stock");
		}

		vendingMachineState.selectItem(id);
	}


	public void insertCoin(Coin coin) {
		vendingMachineState.insertCoin(coin);
	}

	public void dispatchProduct() {
		vendingMachineState.dispatchProduct();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Product getSelectProduct() {
		return inventory.getProduct(selectedItem);
	}

	public void nextStep(VendingMachineState vendingMachineState) {
		this.vendingMachineState = vendingMachineState;
	}


	public void reset() {
		this.balance = 0;
		this.selectedItem = null;
	}

	public int getBalance() {
		return balance;
	}

    public void addBalance(int price) {
        this.balance += price;
    }

    public void refundBalance() {
        System.out.println("Refunding balance");System.out.println("Refunding balance");
    }
}