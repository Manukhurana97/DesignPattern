package entity;

import java.util.*;

public class Question extends Post{
	public String title;
	public Set<Tag> tags;
	public Set<Answer> answers = new HashSet<>();
	public Answer acceptedAnswer;

	public Question(User author, String title, String body, Set<Tag> tags) {
		super(UUID.randomUUID().toString(), author, body);
		this.title = title;
		this.tags = tags;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}


	public synchronized void acceptAnswer(Answer answer) {
		if(!this.author.getId().equals(answer.getAuthor().getId()) && this.acceptedAnswer == null){
			answer.setAccepted(true);
			this.acceptedAnswer = answer;
		}
	}


	public String getTitle() {
		return title;
	}

	public Set<Tag> getTags() {
		return tags;
	}

}