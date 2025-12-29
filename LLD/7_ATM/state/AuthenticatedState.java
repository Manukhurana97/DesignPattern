package state;

import entities.Card;
import enums.*;
import service.AccountService;
import service.AtmService;

public class AuthenticatedState implements AtmServiceState {

    private final AccountService accountService;
    private final AtmService atmService;

    public AuthenticatedState(AtmService atmService, AccountService accountService) {
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
        switch (atmOperations) {
            case CHECK_BALANCE:
                Card card = atmService.getInsertedCard();
                System.out.println("Balance amount: "+card.getAccount().getAmount());
                return;
            case WITHDRAW:
                System.out.println("Enter amount");
                this.atmService.setState(new WithdrawAmountState(atmService, accountService));

        }
    }

    public void enterAmount(int amount) {
        System.out.println("Invalid operation");
        return;
    }

    public void eject() {
        System.out.println("Card is ejected");
        this.atmService.ejectHelper();
    }
}
