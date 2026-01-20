package state;

import entities.OrderDetails;
import enums.OrderStatus;
import service.RestaurantManager;
import service.RestaurantService;

public class OrderReceived implements OrderState{

    private final RestaurantService restaurantService;
    private final OrderDetails orderDetails;
    private final RestaurantManager restaurantManager;

    public OrderReceived(RestaurantService restaurantService, OrderDetails orderDetails, RestaurantManager restaurantManager) {
        this.restaurantService = restaurantService;
        this.orderDetails = orderDetails;
        this.restaurantManager = restaurantManager;
    }

    public void orderAccept() {
        if(restaurantService.orderAccept(orderDetails, restaurantManager.getRestaurant(orderDetails.getRestaurantId()))){
            System.out.println("--- Order Accepted ---");
            orderDetails.updatedOrderState(new OrderPreparing(orderDetails), OrderStatus.ORDER_ACCEPTED);
        }
    }

    public void orderPreparing() {
        System.out.println("Accept the order first");
    }

    public void outForDeliver() {
        System.out.println("Let the order cook first");
    }

    public void delivered() {
        System.out.println("Let the order cook first");
    }

    public void canceled() {
        System.out.println("Order is canceled");
        orderDetails.updatedOrderState(new CanceledState(), OrderStatus.CANCELED);
    }
}
