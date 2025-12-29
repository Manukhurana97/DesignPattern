package ChainOfResponsibility;


public class FiveHundredDispenser extends AbstractCashDispenser {

    private static final int DENOMINATION = 500;

    public void dispense(int amount) {
        int count = amount / DENOMINATION;
        int remaining = amount % DENOMINATION;

        if (count > 0) {
            System.out.println("Dispensing " + count + " notes of 500");
        }

        forward(remaining);
    }
}
