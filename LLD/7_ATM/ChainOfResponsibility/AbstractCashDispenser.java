package ChainOfResponsibility;

public abstract class AbstractCashDispenser implements CashDispenser {

    protected CashDispenser next;

    public void setNext(CashDispenser next) {
        this.next = next;
    }

    protected void forward(int remainingAmount) {
        if (next != null && remainingAmount > 0) {
            next.dispense(remainingAmount);
        }
    }
}
