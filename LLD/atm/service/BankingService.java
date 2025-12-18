package service;

import entity.*;
import java.util.*;

public class BankingService {
	private static final BankingService instance = new BankingService();
	private final Map<String, Account> accounts;
	private final Map<String, Card> cards;
	private final Map<Card, Account> cardAccount;

    BankingService() {
    	this.accounts = new HashMap<>();
    	this.cards = new HashMap<>();
    	this.cardAccount = new HashMap<>();
    }

    public static BankingService getInstance() {
    	return instance;
    }

    public Account createAccount(int amount) {
    	Account account = new Account(amount);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("account created: "+ account.getAccountNumber());
    	return account;
    }

    public Account getAccount(Card card) {
        return cardAccount.getOrDefault(card, null);
    }

    public Card createCard(String cardNumber, String pin) {
        Card card  = new Card(cardNumber, pin);
        cards.put(cardNumber, card); 
        System.out.println("card created: "+ cardNumber);
        return card;
    }

    public Card getCard(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }

    public void mapCardWithAccount(Card card, Account account) {
        cardAccount.put(card, account);
        System.out.println("card map to account");
    }

    public int getBalance(Card card) {
        if(!cardAccount.containsKey(card)) {
            return -1;
        }

        Account account = cardAccount.get(card);
        return account.getBalance();
    } 

    public void depositMoney(Card card, int amount) {
        if(!cardAccount.containsKey(card)) {
            System.out.println("card is not mapped with any account");
            return;
        }

        Account account = cardAccount.get(card);
        account.deposit(amount);
    }
}