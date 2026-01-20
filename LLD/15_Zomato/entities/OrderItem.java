package entities;

public class OrderItem {
    private final String food;
    private final int quantity;
    private final String specialRequest;

    public OrderItem(String food, String specialRequest, int quantity) {
        this.food = food;
        this.quantity = quantity;
        this.specialRequest = specialRequest;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }
}
