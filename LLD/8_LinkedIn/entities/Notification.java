package entities;

import java.time.*;
import java.util.*;

import enums.*;

public class Notification {
	private final String id;
	private final String description;
	private final LocalDateTime createdAt;
	private boolean isRead;
	private NotificationType type;

	public Notification(String description, NotificationType type) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.isRead = false;
		this.type = type;
	}

	public String getId() { return id; }
	public String getDescription() { return description; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public boolean isRead() { return isRead; }
	public void markAsRead() { isRead = true; }
	public NotificationType getType() { return type; }
}