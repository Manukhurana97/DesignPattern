package state;

import enums.*;
import service.*;
import entities.*;

public class WithdrawAmountState implements AtmServiceState{

	private final AccountService accountService;
    private final AtmService atmService;

    public WithdrawAmountState(AtmService atmService, AccountService accountService) {
        this.accountService = accountService;
        this.atmService = atmService;
    }

    public void insertCard(String cardNumber) {
        System.out.println("Card is Already Inserted");
        return;
    }

    public void enterPin(String pin) {
        System.out.println("Pin is already validated");
        return;
    }

    public void selectOperation(AtmOperations atmOperations) {
    	System.out.println("Already Selected");
     	return;
    }

    public void enterAmount(int amount) {
    	if(amount > 10000) {
    		System.out.println("Max 10000 is allowed at a time");
            return;
    	} 
    	if(amount % 500 != 0 && amount % 100 != 0) {
    		System.out.println("Enter amount multiple of 100 or 500");
    	    return;
        }

        if(!accountService.canWithDraw(atmService.getInsertedCard().getAccount(), amount)) {
            System.out.println("insufficient funds");
            atmService.setState(new AuthenticatedState(atmService, accountService));
            return;
        }

    	System.out.println("getting amount...");
    	delay();
        accountService.withdrawAmount(atmService.getInsertedCard().getAccount(), amount);
        atmService.setState(new AuthenticatedState(atmService, accountService));
    	System.out.printf("Please collect the card and cash. \nselect the next operation \n");
        return;
    }

    
    public void eject() {
        atmService.ejectHelper();
    }

    public void delay() {
    	try {
    		Thread.sleep(2000);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}