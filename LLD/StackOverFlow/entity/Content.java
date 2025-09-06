package entity;

import java.time.LocalDateTime;


public class Content {
	protected final String id;
	protected final User author;
	protected final String body;
	protected final LocalDateTime createdTime;

	public Content(String id, User author, String body) {
		this.id = id;
		this.author = author;
		this.body = body;
		this.createdTime = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public User getAuthor() {
		return author;
	}

	public String getBody() {
		return body;
	}
}