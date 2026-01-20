package enums;

public enum Rating {
    FIVE(5), FOUR(4), Three(3);

    private final int value;

    Rating(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
