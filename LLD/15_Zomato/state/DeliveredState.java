package state;

import entities.OrderDetails;

import java.time.LocalDateTime;

public class DeliveredState implements OrderState {
    OrderDetails orderDetails;
    public DeliveredState(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    
    public void orderAccept() {
        System.out.println("Order already delivered");
    }

    
    public void orderPreparing() {
        System.out.println("Order already delivered");
    }

    
    public void outForDeliver() {
        System.out.println("Order already delivered");
    }

    
    public void delivered() {
        orderDetails.setDeliverAt(LocalDateTime.now());
        System.out.println("--- Order Delivered at " + orderDetails.getDeliverAt() + " at location :"+ orderDetails.getDeliverAddress() +" ---");
        this.orderDetails.getRider().setIsAvailable(true);
    }

    
    public void canceled() {
        System.out.println("Order already delivered");
    }
}
