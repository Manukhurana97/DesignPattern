package state;

import entity.*;
import enums.*;
import service.*;

public class Operations implements AtmState {

	@Override
	public void insertCard(BankingService bankingService, ATMService atmService, String cardNumber) {
		System.out.println("\nCard already inserted");
		return;
	}

	@Override
	public void enterPin(BankingService bankingService, ATMService atmService, String pin){
		System.out.println("pin already validated");
	}
	@Override
	public void selectOperation(BankingService bankingService, ATMService atmService, OperationType type, int... args){
		
	}
	@Override
	public void ejectCard(BankingService bankingService, ATMService atmService){
		System.out.println("Ejecting Card...");
		atmService.eject();
	}