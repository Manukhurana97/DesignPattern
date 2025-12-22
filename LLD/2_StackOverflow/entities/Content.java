package entities;

import java.util.*;

import entities.*;

public class Content {
	private final String id;
	private final String description;
	private final User author;
	private final Long createdDate;

	Content(String description, User author) {
		this.id = UUID.randomUUID().toString();
		this.author = author;
		this.description = description;
		this.createdDate = System.currentTimeMillis();
	}

	public String getId() { return id;}
	public String getDescription() { return description;}
	public User getAuthor() { return author; }
}