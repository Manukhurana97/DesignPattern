package service;

import entities.OrderDetails;
import entities.OrderItem;
import entities.Rider;
import state.OrderReceived;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {

    private static OrderService INSTANCE;
    private final RestaurantManager restaurantManager;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final DeliveryPartnerManager deliveryPartnerManager;
    private final Map<String, OrderDetails> orders = new ConcurrentHashMap<>();

    OrderService() {
        this.restaurantManager = RestaurantManager.getInstance();
        this.userService = UserService.getInstance();
        this.restaurantService = RestaurantService.getInstance();
        this.deliveryPartnerManager = DeliveryPartnerManager.getInstance();
    }

    public synchronized static OrderService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrderService();
        }

        return INSTANCE;
    }

    public OrderDetails orderFood(String userId, String restaurantId, List<OrderItem> orderItems) {
        if(!userService.validAccount(userId)) {
            System.out.println("### Invalid User Id ###");
            return null;
        }

        if(!restaurantManager.validRestaurant(restaurantId)) {
            System.out.println("### Invalid Restaurant Id ###");
            return null;
        }

        if(!restaurantManager.getRestaurant(restaurantId).isAvailable()) {
            System.out.println("### Restaurant is Not available ###");
            return null;
        }

        OrderDetails orderDetails = new OrderDetails(userId, restaurantId, orderItems, restaurantManager.getRestaurant(restaurantId).getAddress(), userService.getUserId(userId).getAddress());
        orderDetails.setState( new OrderReceived(restaurantService, orderDetails, restaurantManager));
        orders.put(orderDetails.getId(), orderDetails);

        System.out.println(" --- Order initiated ---");
        orderDetails.getState().orderAccept();

        Optional<Rider> rider = deliveryPartnerManager.findDeliveryPartner();
        if(!rider.isEmpty()) {
            System.out.println("---> " +rider.get().getName() +" is Assigned. ---");
            orderDetails.setRider(rider.get());
        }

        orderDetails.getState().orderPreparing();
        orderDetails.getState().outForDeliver();
        orderDetails.getState().delivered();

        return orderDetails;
    }
}
