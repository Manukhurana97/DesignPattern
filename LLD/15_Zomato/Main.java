import entities.Food;
import entities.OrderItem;
import entities.Restaurant;
import entities.User;
import service.DeliveryPartnerManager;
import service.OrderService;
import service.RestaurantManager;
import service.UserService;

import java.util.List;

public class Main {
    static void main(String[] args) {
        UserService userService = UserService.getInstance();
        User user1 = userService.createAccount("abc", "123123123", "7, lok Kalyan Marg, New Delhi");
        User user2 = userService.createAccount("dbc", "123123122", "Rashtrapati Bhavan, New Delhi");
        System.out.println();

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        Restaurant restaurant = restaurantManager.registerRestaurant("Haldiram", "Kartavya Path, E Block, Central Secretariat, New Delhi");
        System.out.println();

        Food curd = restaurantManager.addItemMenu(restaurant.getId(), "Curd", 10, 20);
        restaurantManager.addItemMenu(restaurant.getId(), "Chole Bhature", 100, 10);
        restaurantManager.addItemMenu(restaurant.getId(), "Rajma Chawal", 100, 10);
        restaurantManager.addItemMenu(restaurant.getId(), "Raj kachori", 100, 10, List.of(curd));
        restaurantManager.addItemMenu(restaurant.getId(), "Coke", 100, 20);
        restaurantManager.addItemMenu(restaurant.getId(), "Lassi", 100, 20);
        System.out.println();

        DeliveryPartnerManager deliveryPartnerManager = DeliveryPartnerManager.getInstance();
        deliveryPartnerManager.addRider("abc", "DL01AA0001", "100");
        deliveryPartnerManager.addRider("abcd", "DL01AA0002", "100");
        deliveryPartnerManager.addRider("abcdf", "DL01AA0003", "100");
        System.out.println();


        OrderService orderService = OrderService.getInstance();
        orderService.orderFood(user2.getId(), restaurant.getId(),
                List.of(new OrderItem("Chole Bhature", "More Spicy", 2),
                        new OrderItem("Rajma Chawal", "", 1),
                        new OrderItem("Lassi", "No ice", 3)));

        System.out.println();
        restaurantManager.setRestaurantUnavailable(restaurant.getId());
        orderService.orderFood(user2.getId(), restaurant.getId(),
                List.of(new OrderItem("Chole Bhature", "More Spicy", 2),
                        new OrderItem("Rajma Chawal", "", 1),
                        new OrderItem("Lassi", "No ice", 3)));

        System.out.println();
        restaurantManager.setRestaurantAvailable(restaurant.getId());
        orderService.orderFood(user2.getId(), restaurant.getId(),
                List.of(new OrderItem("Chole Bhature", "More Spicy", 2),
                        new OrderItem("Rajma Chawal", "", 1),
                        new OrderItem("Lassi", "No ice", 3)));
    }
}