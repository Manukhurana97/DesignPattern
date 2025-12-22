package entities;

import java.util.*;
import enums.EventType;

public class Event {
	private final EventType type;
	private final Set<User> user;
	private final Post post;

	public Event(EventType type, Set<User> user, Post post) {
		this.type = type;
		this.user = user;
		this.post = post;
	}

	public EventType getEventType() { return type; }
	public Set<User> getUser() { return user; }
	public Post getPost() { return post; }
}