package state;

import entities.OrderDetails;
import enums.OrderStatus;

public class OrderPreparing implements OrderState {

    private OrderDetails orderDetails;

    public OrderPreparing(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public void orderAccept() {
        System.out.println("Order already accepted");
    }

    
    public void orderPreparing() {
        System.out.println("--- Order prepared, waiting for driver To PickUp ---");
        orderDetails.updatedOrderState(new OutForDeliveryState(orderDetails), OrderStatus.OUT_FOR_DELIVERY);
    }

    
    public void outForDeliver() {
        System.out.println("Invalid operation");
    }

    
    public void delivered() {
        System.out.println("Invalid operation");
    }

    
    public void canceled() {
        orderDetails.updatedOrderState(new CanceledState(), OrderStatus.CANCELED);
    }
}
