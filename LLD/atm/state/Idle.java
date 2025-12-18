package state;

import entity.*;
import enums.*;
import service.*;

public class Idle implements AtmState {

	@Override
	public void insertCard(BankingService bankingService, ATMService atmService, String cardNumber) {
		System.out.println("\nCard is inserted, validating...");
		Card card = bankingService.getCard(cardNumber);
		if(card == null) {
			System.out.println("invalid card number");
			ejectCard(bankingService, atmService);
			return;
		}

		Account account = bankingService.getAccount(card);
		if(account == null) {
			System.out.println("card is not mapped to any account");
			ejectCard(bankingService, atmService);
			return;
		}


		atmService.setCard(card);
		atmService.setNextStep(new AuthenticationState());

	}

	@Override
	public void enterPin(BankingService bankingService, ATMService atmService, String pin){
		System.out.println("invalid operation");
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
}