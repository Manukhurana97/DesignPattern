package entities;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Restaurant {
    private String id;
    private String name;
    private boolean isAvailable;
    private Map<String, Food> menu;
    private boolean autoAccept;
    private String address;

    public Restaurant(String name, boolean isAvailable, boolean autoAccept, String address) {
        this.address = address;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isAvailable = isAvailable;
        this.menu = new ConcurrentHashMap<>();
        this.autoAccept = autoAccept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addFoodInMenu(Food food) {
        menu.put(food.getName(), food);
    }

    public boolean hasItem(String food) { return menu.containsKey(food);}

    public Food getFood(String food) { return menu.get(food); }

    public boolean isAutoAccept() {
        return autoAccept;
    }

    public void setAutoAccept(boolean autoAccept) {
        this.autoAccept = autoAccept;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // transactional
    public boolean reduceFoodQuantity(String food, int quantity) {
        if(menu.containsKey(food) && menu.get(food).getQuantity() >= quantity) {
            menu.get(food).reduceQuantity(quantity);
            return true;
        }
        return false;
    }
}
