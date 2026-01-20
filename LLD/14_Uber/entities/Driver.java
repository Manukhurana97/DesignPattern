package entities;

import enums.Rating;

import java.util.UUID;

public class Driver {
    private final String id;
    private String name;
    private boolean isAvailable;
    private Rating rating;

    public Driver(String name, boolean isAvailable, Rating rating) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isAvailable = isAvailable;
        this.rating = rating;
    }

    public String getId() { return id; }

    public boolean isAvailable() { return isAvailable; }

    public String getName() { return name; }

    public Rating getRating() { return rating;}

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
