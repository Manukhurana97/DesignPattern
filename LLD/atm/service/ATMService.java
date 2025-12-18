package service;

import entity.*;
import enums.*;
import state.*;

public class ATMService {
	private Card currentCard;
    private AtmState atmState;
    private static final ATMService instance = new ATMService();
    private static final BankingService bankingService;


    ATMService() {
    	this.bankingService = BankingService.getInstance();
    	this.atmState = new Idle();
    }


	public static ATMService getInstance() {
		return instance;
	}

	public void insertCard(String cardNumber) {
		atmState.insertCard(bankingService, this, cardNumber);
	}

	public void enterPin(String number) {

	}

	public void selectOperation(OperationType type) {

	}

	public void selectOperation(OperationType type, int amount) {

	}

	public void setCard(Card card) {
		this.currentCard = card;
	}

	public void setNextStep(AtmState state) {
		this.atmState = atmState;
	}

	public void eject() {
		setCard(null);
		setNextStep(new Idle());
	}
}