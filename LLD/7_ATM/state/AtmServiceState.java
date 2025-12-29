package state;

import enums.AtmOperations;

public interface AtmServiceState {
	void insertCard(String cardNumber);
	void enterPin(String pin);
	void selectOperation(AtmOperations atmOperations);
	void enterAmount(int amount);
	void eject();
}