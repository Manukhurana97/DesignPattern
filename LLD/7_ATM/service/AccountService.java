package service;

import entities.*;
import ChainOfResponsibility.*;

import java.util.*;;

public class AccountService {
	private static AccountService INSTANCE;
	private final Map<String, Account> accounts;
	private final Map<String, Card> cards;

	CashDispenser fiveHundred = new FiveHundredDispenser();
	CashDispenser hundred = new HundredDispenser();



	public AccountService() {
		this.accounts = new HashMap<>();
		this.cards = new HashMap<>();
		fiveHundred.setNext(hundred);
	}

	public static AccountService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AccountService();
		}

		return INSTANCE;
	}

	public Account createAccount(double amount) {
		Account account = new Account(amount);
		accounts.put(account.getAccountId(), account);

		System.out.printf("Account created: %s\n", account.getAccountId());
		return account;
	}

	public boolean canWithDraw(Account account ,int amount) {
		return account.getAmount() >= amount;
	}

	public void withdrawAmount(Account account, int amount) {
		accounts.put(account.getAccountId(), account.withDraw(amount));
		fiveHundred.dispense(amount);
	}

	public Card addCard(Account account, String cardNumber, String pin) {
		Card card = new Card(account, cardNumber, pin);

		Account userAccount = accounts.get(account.getAccountId());
		if(userAccount.addCard(card)) {
			updateCard(card);
		}

		System.out.printf("Card mapped to account %s\n", card.getCardNumber());
		return card;
	}

	public Card activateCard(String cardNo) {
		if(!cards.containsKey(cardNo)) {
			System.out.println("Invalid card number");
			return null;
		}

		Card card = cards.get(cardNo);
		card.activateCard();

		updateCard(card);
		System.out.println(card.getCardNumber()+" card is activated.");
		return card;
	}

	public Optional<Card> getCard(String cardNumber) {
		return Optional.of(cards.get(cardNumber));
	}

	public void updateCard(Card card) {
		cards.put(card.getCardNumber(), card);
	}


}