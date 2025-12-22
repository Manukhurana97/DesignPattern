package entities;

import java.util.*;

public class Answer extends Post{

	public Answer(String description, User author) {
		super(description, author);
	}

	public Answer(String description, User author, Set<Tag> tags) {
		super(description, author, tags);
	}
}