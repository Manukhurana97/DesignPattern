package entities;

import java.util.*;

public class Account {
	private final String accountId;
	private double amount;
	private Map<String, Card> cards;

	public Account(double amount) {
		this.accountId = UUID.randomUUID().toString();
		this.amount = amount;
		cards = new HashMap<>();
	}

	public String getAccountId() { return accountId; }

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public Map<String, Card> getCards() {
		return cards;
	}

	public boolean addCard(Card card) {
		if(cards.containsKey(card.getCardNumber())) {
			System.out.println("Card already mapped with Account");
			return false;
		}

		cards.put(card.getCardNumber(), card);
		return true;
	}

	public Account withDraw(int withDrawAmount) {
		this.amount -= withDrawAmount;
		return this;
	}
}