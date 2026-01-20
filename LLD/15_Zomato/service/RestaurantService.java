package service;

import entities.OrderDetails;
import entities.OrderItem;
import entities.Restaurant;
import state.OrderPreparing;

public class RestaurantService {

    public static RestaurantService INSTANCE;

    public synchronized static RestaurantService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RestaurantService();
        }

        return INSTANCE;
    }


    public synchronized boolean orderAccept(OrderDetails orderDetails, Restaurant restaurant) {
        if(!restaurant.isAvailable()) return false;
        for(OrderItem item: orderDetails.getItems()) {
            if(!restaurant.reduceFoodQuantity(item.getFood(), item.getQuantity())) {
                return false;
            }
        }
        return true;
    }
}
