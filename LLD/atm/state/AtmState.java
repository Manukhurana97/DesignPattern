package state;

import enums.*;
import service.*;

public interface AtmState {
	void insertCard(BankingService bankingService, ATMService service, String cardNumber);
	void enterPin(BankingService bankingService, ATMService service, String pin);
	void selectOperation(BankingService bankingService, ATMService service, OperationType type, int... args);
	void ejectCard(BankingService bankingService, ATMService service);
}