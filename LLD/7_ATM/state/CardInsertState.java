package state;

import enums.AtmOperations;
import service.*;
import entities.*;
import java.util.*;

public class CardInsertState implements AtmServiceState {

	private final AccountService accountService;
	private final AtmService atmService;

	public CardInsertState(AtmService atmService, AccountService accountService) {
		this.accountService = accountService;
		this.atmService = atmService;
	}

	public void insertCard(String cardNumber) {
		Optional<Card> card = accountService.getCard(cardNumber);
		if(card.isEmpty()) {
			System.out.println("Invalid card number..");
			return;
		}

		atmService.cardInserted(card.get());
		atmService.setState(new EnterPinState(atmService, accountService));

	}

	public void enterPin(String pin) {
		System.out.println("Invalid Operation");
		return;
	}
	
	public void selectOperation(AtmOperations atmOperations) {
		System.out.println("Invalid Operation");
		return;
	}

	public void enterAmount(int amount) {
		System.out.println("Invalid operation");
		return;
	}

	public void eject() {
		System.out.println("Invalid Operation");
		return;
	}
}