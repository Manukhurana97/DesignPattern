package enums;

public enum Note {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200);

    private final int amount;

    Note(int amount) {
        this.amount = amount;
    }

    public int getAmount() { return amount; }
}
