package entities;

import java.time.LocalDateTime;

public class Activity {
    private final User user;
    private final String description;
    private final LocalDateTime timeStamp;

    public Activity(String description, User user) {
        this.description = description;
        this.user = user;
        this.timeStamp = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}