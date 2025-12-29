package ChainOfResponsibility;

public interface CashDispenser {

    void setNext(CashDispenser next);

    void dispense(int amount);
}
