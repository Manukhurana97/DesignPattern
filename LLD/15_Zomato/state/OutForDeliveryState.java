package state;

import entities.OrderDetails;
import enums.OrderStatus;

import java.time.LocalDateTime;

public class OutForDeliveryState implements OrderState{

    OrderDetails orderDetails;
    
    public OutForDeliveryState(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
    public void orderAccept() {
        System.out.println("Invalid operation");
    }

    
    public void orderPreparing() {
        System.out.println("Invalid operation");
    }

    
    public void outForDeliver() {
        System.out.println("--- Order is out for delivery ---");
        orderDetails.updatedOrderState(new DeliveredState(orderDetails), OrderStatus.DELIVERED);
    }

    
    public void delivered() {
        System.out.println("Order already delivered");
    }

    
    public void canceled() {
        System.out.println("Order already delivered");
    }
}
