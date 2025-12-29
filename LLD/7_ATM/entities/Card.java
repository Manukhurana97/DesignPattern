package entities;


public class Card {
    private Account account;
    private final String cardNumber;
    private String pin;
    private boolean isActive;

    public Card(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.isActive = false;
    }

    public Card(Account account, String cardNumber, String pin) {
        this.account = account;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.isActive = true;
    }

    public final Account getAccount() {
        return account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPin() {
        return pin;
    }

    public void activateCard() {
        this.isActive = true;
    }

    public void deActivateCard() {
        this.isActive = false;
    }

    public boolean isActivate() {
        return this.isActive;
    }
}