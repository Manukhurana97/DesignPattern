package entity;

import enums.EventType;


public class Event {
	private final EventType type;
	private final User user;
	private final Post targetPosts;

	public Event(EventType type, User user, Post targetPosts) {
		this.type = type;
		this.user = user;
		this.targetPosts = targetPosts;
	}	

	public EventType getType() {
		return type;
	}

	public User user() {
		return user;
	}

	public Post getTargetPost() {
		return targetPosts;
	}
}