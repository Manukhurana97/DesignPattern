package service;

import entities.*;
import enums.*;
import state.*;


public class VendingMachineService {

	private String selectedItem;
	private int balance;
	private static VendingMachineService INSTANCE;
	private final Inventory inventory = Inventory.getInstance();
	private VendingMachineState vendingMachineState;


	public VendingMachineService() {
		this.vendingMachineState = new IdleState(this);
	}


	public static synchronized VendingMachineService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new VendingMachineService();
		}

		return INSTANCE;
	}


	public Item addItem(String itemId, String name, int quantity, int amount) {
		Item item = new Item(itemId, name, amount);
		inventory.addItem(itemId, amount, item);

		System.out.printf("%s added successfully \n", name);
		return item;
	}


	public void selectItem(String itemId) {
		vendingMachineState.selectItem(itemId);
	}

	public void insertCoin(Coin coin) {
		vendingMachineState.insertCoin(coin);
	}

	public void dispense() {
		vendingMachineState.dispatch();
		delay();
		System.out.println("dispatched, thanks for coming...");
	}

	public void delay() {
		try{
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void refund() {
		System.out.println("checking for refund, please wait...");
		vendingMachineState.refund();
	}


	public Inventory getInventory() {
		return inventory;
	}

	public String getSelected() {
		return this.selectedItem;
	}

	public void setSelectItem(String code) {
		this.selectedItem = code;
		this.balance = inventory.getAmount(code);
	}

	public void setState(VendingMachineState vendingMachineState) {
		this.vendingMachineState = vendingMachineState;
	}

	public int getBalance() {
		return this.balance;
	}

	public void updateBalance(int balance) {
		this.balance = balance;
	}

	public void reset() {
		this.balance = 0;
		this.selectedItem = "";
	}
} 