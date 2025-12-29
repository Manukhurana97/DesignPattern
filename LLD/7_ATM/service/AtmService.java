package service;

import enums.*;
import entities.*;
import state.*;


import java.util.Optional;

public class AtmService {

	private int tryLeft = 3;
	private Card insertedCard;

	private static AtmService INSTANCE;
	private static AccountService accountService;
	private static AtmServiceState atmServiceState;


	AtmService() {
		accountService = AccountService.getInstance();
		atmServiceState = new CardInsertState(this, accountService);
	}


	public static AtmService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AtmService();
		}

		return INSTANCE;
	}

// ------------------------------------- card action -------------------------------------------
	public void insertCard(String cardNumber) {
		Optional<Card> card = accountService.getCard(cardNumber);

		if(card.isEmpty()) {
			System.out.println("### No card found ##");
			return;
		} 

		if(card.get().getAccount() == null) {
			System.out.println("### Card not mapped to any account ##");
			return;
		}

		if(!card.get().isActivate()) {
			System.out.println("### Card not Active ##");
			return;
		}

		this.insertedCard = card.get();
		System.out.println("Card is inserted, validating...");
		atmServiceState.insertCard(cardNumber);
		System.out.println("Enter pin:");
	}

	public void insertPin(String pin) {
		System.out.print("Validating pin -> ");
		atmServiceState.enterPin(pin);
	}

	public void action(AtmOperations operation) {
		atmServiceState.selectOperation(operation);
	}

	public void enterAmount(int amount) {
		atmServiceState.enterAmount(amount);
	}

	private void eject() {
		this.ejectHelper();
	}

//	--------------------------------------- helper function -----------------------------------

	public void cardInserted(Card card) {
		this.insertedCard = card;
	}

	public Card getInsertedCard() {
		return insertedCard;
	}

	public void setState(AtmServiceState atmServiceState) {
		this.atmServiceState = atmServiceState;
	}

	public void ejectHelper() {
		this.insertedCard = null;
		this.setState(new CardInsertState(this, accountService));
	}

	public void resetTry() { tryLeft = 3; }
	public int invalidPin() {
		tryLeft -= 1;
		return tryLeft;
	}
	public int getTrys() { return tryLeft; }

}