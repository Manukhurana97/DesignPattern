package state;

public class CanceledState implements OrderState{
    
    public void orderAccept() {
        System.out.println("Order is canceled");
    }

    
    public void orderPreparing() {
        System.out.println("Order is canceled");
    }

    
    public void outForDeliver() {
        System.out.println("Order is canceled");
    }

    
    public void delivered() {
        System.out.println("Order is canceled");
    }

    
    public void canceled() {
        System.out.println("Order is canceled");
    }
}
