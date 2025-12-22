package entities;

import java.util.*;

import entities.*;


public class Question extends Post{
	private final String title;
	private final List<Answer> answers = new ArrayList<>();
	private Answer acceptedAnswer;

	public Question(String title, String description, User author) {
		this.title = title;
		super(description, author);
	}

	public Question(String title, String description, User author, Set<Tag> tags) {
		this.title = title;
		super(description, author, tags);
	}

	public List<Answer> getAnswer() { 
		return answers; 
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public String getTitle() {
		return title;
	}

	public void acceptAnswer(Answer answer) {
		this.acceptedAnswer = answer;
	}
}