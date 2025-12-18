package entity;

import java.util.*;

public class Account {
	private int balance;
	private final String accountNumber;

	public Account(int balance) {
		this.accountNumber = UUID.randomUUID().toString();
		this.balance = balance;
	}

	public boolean withdraw(int amount) {
        if(balance < amount) {
        	return false;
        }

        balance -= amount;
        return true;
	}

	public void deposit(int amount) {
		balance += amount;
	}

    public int getBalance() {
    	return balance;
    }

    public String getAccountNumber() {
    	return accountNumber;
    }
}