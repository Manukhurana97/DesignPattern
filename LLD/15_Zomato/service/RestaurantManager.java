package service;

import entities.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantManager {
    private static RestaurantManager INSTANCE;
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    public synchronized static RestaurantManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestaurantManager();
        }

        return INSTANCE;
    }

    public boolean validRestaurant(String restaurantId) {
        return restaurants.containsKey(restaurantId);
    }

    public Restaurant getRestaurant(String restaurantId) {
        return restaurants.get(restaurantId);
    }

    public Restaurant registerRestaurant(String name, String address) {
        Restaurant restaurant = new Restaurant(name, true, true, address);
        restaurants.put(restaurant.getId(), restaurant);
        System.out.printf("%s onboarded successfully \n", name);
        return restaurant;
    }

    public Food addItemMenu(String restaurantId, String name, double price, int quantity) {
        return addItemMenu(restaurantId, name, price, quantity, List.of());
    }

    public Food addItemMenu(String restaurantId, String name, double price, int quantity, List<Food> addons) {
        if(!restaurants.containsKey(restaurantId)) {
            System.out.println("Invalid Restaurant Id");
            return null;
        }

        Restaurant restaurant = getRestaurant(restaurantId);

        if(restaurant.hasItem(name)) {
            System.out.printf("%s already in menu \n", name);
            return restaurant.getFood(name);
        }

        Food food = new Food(name, price, quantity, addons);
        restaurant.addFoodInMenu(food);

        System.out.printf("Adding %s in %s menu \n", name, restaurant.getName());
        return food;
    }

    public void setRestaurantUnavailable(String restaurantId) {
        if(!restaurants.containsKey(restaurantId)) {
            System.out.println("Invalid Restaurant Id");
            return ;
        }

        Restaurant restaurant = getRestaurant(restaurantId);
        restaurant.setAvailable(false);
        System.out.println(" --- Restaurant went offline ---");
    }

    public void setRestaurantAvailable(String restaurantId) {
        if(!restaurants.containsKey(restaurantId)) {
            System.out.println("Invalid Restaurant Id");
            return ;
        }

        Restaurant restaurant = getRestaurant(restaurantId);
        restaurant.setAvailable(true);
        System.out.println(" --- Restaurant is Online ---");
    }
}
