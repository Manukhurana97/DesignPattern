package entities;

import enums.OrderStatus;
import state.OrderReceived;
import state.OrderState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.UUID;

public class OrderDetails {
    private final String id;
    private OrderStatus status;
    private final String userId;
    private final String restaurantId;
    private final LocalDateTime orderAt;
    private final List<OrderItem> items;
    private LocalDateTime deliverAt;
    private final String pickUpAddress;
    private final String deliverAddress;
    private Rider rider;
    private OrderState state;
    private List<Observer> observers;

    public OrderDetails(String userId, String restaurantId, List<OrderItem> items, String pickUpAddress, String deliverAddress) {
        this.pickUpAddress = pickUpAddress;
        this.deliverAddress = deliverAddress;
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.ORDER_RECEIVED;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.orderAt = LocalDateTime.now();
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(LocalDateTime deliverAt) {
        this.deliverAt = deliverAt;
    }


    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void updatedOrderState(OrderState state, OrderStatus orderStatus) {
        this.setState(state);
        this.setStatus(status);
    }

}
