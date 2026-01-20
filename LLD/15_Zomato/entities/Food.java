package entities;

import java.util.List;
import java.util.UUID;

public class Food {
    private String name;
    private double price;
    private int quantity;
    private List<Food> isAddon;

    public Food(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Food(String name, double price, int quantity, List<Food> isAddon) {
        this(name, price, quantity);
        this.isAddon = isAddon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public List<Food> getIsAddon() {
        return isAddon;
    }

    public void setIsAddon(List<Food> isAddon) {
        this.isAddon = isAddon;
    }
}
