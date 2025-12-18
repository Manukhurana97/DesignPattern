package state;

import entity.*;
import enums.*;
import service.*;


public class AuthenticationState implements AtmState {

	@Override
	public void insertCard(BankingService bankingService, ATMService atmService, String cardNumber) {
		System.out.println("\nCard already inserted");
		return;
	}

	@Override
	public void enterPin(BankingService bankingService, ATMService atmService, String pin){
		System.out.println("Validating pin...");

		Card card = atmService.getCard();
		if(!card.getPin().equals(pin)) {
			System.out.println("invalid pin..");
			ejectCard(bankingService, atmService);
		}

		System.out.println("validated");
		atmService.setNextStep(new AuthenticationState());
	}
	@Override
	public void selectOperation(BankingService bankingService, ATMService atmService, OperationType type, int... args){
		System.out.println("invalid operation");
	}
	@Override
	public void ejectCard(BankingService bankingService, ATMService atmService){
		System.out.println("Ejecting Card...");
		atmService.eject();
	}