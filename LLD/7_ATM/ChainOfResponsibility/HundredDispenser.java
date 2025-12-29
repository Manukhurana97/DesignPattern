package ChainOfResponsibility;


public class HundredDispenser extends AbstractCashDispenser {

    private static final int DENOMINATION = 100;

    public void dispense(int amount) {
        int count = amount / DENOMINATION;
        int remaining = amount % DENOMINATION;

        if (count > 0) {
            System.out.println("Dispensing " + count + " notes of 100");
        }

        if (remaining > 0) {
            System.out.println("Cannot dispense remaining amount: " + remaining);
        }
    }
}
