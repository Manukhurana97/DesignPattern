package state;

import enums.AtmOperations;
import service.*;
import entities.*;

public class EnterPinState implements AtmServiceState {

	private final AccountService accountService;
	private final AtmService atmService;

	public EnterPinState(AtmService atmService, AccountService accountService) {
		this.accountService = accountService;
		this.atmService = atmService;
	}
	
	public void insertCard(String cardNumber) {
		System.out.println("Card is already inserted...");
		return;
	}
	
	public void enterPin(String pin) {
		Card card = atmService.getInsertedCard();
		if(!card.getCardPin().equals(pin)) {
			System.out.println("Invalid Pin");
			if(atmService.invalidPin() <= 0){
				System.out.println("card is block, please contact bank");
				card.deActivateCard();
				this.eject();
				accountService.updateCard(card);
			}
			return;
		}

		atmService.resetTry();
		atmService.setState(new AuthenticatedState(atmService, accountService));
	}
	
	public void selectOperation(AtmOperations atmOperations) {
		System.out.println("Invalid operation");
		return;
	}

	public void enterAmount(int amount) {
		System.out.println("Invalid operation");
		return;
	}

	public void eject() {
		System.out.println("ejecting card...");
		atmService.ejectHelper();
	}
}
