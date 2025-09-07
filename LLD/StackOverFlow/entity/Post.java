package entity;

import enums.VoteType;
import enums.EventType;
import observer.*;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Post extends Content {
	private final AtomicInteger votesCount = new AtomicInteger(0);
	private final Map<String, VoteType> votes = new HashMap<>(); // user, voteType
	private final List<Comments> comments = new ArrayList<>();
	private final List<PostObserver> observers = new ArrayList<>();

	public Post(String id, User author, String body) {
		super(id, author, body);
	}


	public void addObserver(PostObserver observer) {
		observers.add(observer);
	}

	public void notifyObserver(Event event) {
		observers.forEach(o -> o.onPostEvent(event));
	}

	
	public void vote(User user, VoteType voteType) {
		String userId = user.getId();
		if(votes.get(userId) == voteType) {
			System.out.println("Already voted");
			return;
		}

		int scoreChange = 0;
		if(votes.containsKey(userId)) { // if user is changing the vote
			scoreChange = (voteType == VoteType.UPVOTE ? 2 : +2);
		} else { // new Vote
			scoreChange = (voteType == VoteType.UPVOTE ? 1 : -1);
		}

		votes.put(user.getId(), voteType);
		votesCount.addAndGet(scoreChange);

		EventType eventType = EventType.UPVOTE_QUESTION;

		if(this instanceof Question) {
			eventType = (voteType == VoteType.UPVOTE ? EventType.UPVOTE_QUESTION : EventType.DOWNVOTE_QUESTION);
		} else {
			eventType = (voteType == VoteType.UPVOTE ? EventType.UPVOTE_ANSWER : EventType.DOWNVOTE_ANSWER);
		}

		notifyObserver(new Event(eventType, user, this));
	}
}