package entity;

import java.util.UUID;
import java.time.LocalDateTime;

public class Comment {
	private final String id;
	private final String description;
	private final LocalDateTime timestamp;
	private final User user;

	public Comment(String description, User user) {
		this.id = UUID.randomUUID().toString();
	    this.description = description;
	    this.timestamp = LocalDateTime.now();
        this.user = user;
	}

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }
}