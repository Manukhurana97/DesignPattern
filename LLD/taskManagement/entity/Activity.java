package entity;

import java.time.LocalDateTime;

public class Activity {
	private final LocalDateTime timeStamp;
    private final String description;

    public Activity(String description) {
    	this.timeStamp = LocalDateTime.now();
        this.description = description;   
    }

    public LocalDateTime getTimestamp() {
    	return timeStamp;
    }

    public String getDescription() {
    	return description;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "timeStamp=" + timeStamp +
                ", description='" + description + '\'' +
                '}';
    }
}