package entities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import entities.*;
import enums.*;


public class Post extends Content{

	private final AtomicInteger voteCount = new AtomicInteger(0);
	private final List<Comment> comments = new ArrayList<>();
	private final Set<Tag> tags = new HashSet<>();
	private final Map<String, VoteType> votes = new HashMap<>();

	Post(String description, User author) {
		super(description, author);
	}

	Post(String description, User author, Set<Tag> tags) {
		this(description, author);
		tags = new HashSet<>(tags);
	}

	public Set<Tag> getTags() {
		return tags;
	}


	public void vote(User user, VoteType type) {
		int score = 0;

        if(votes.containsKey(user.getId())) { 
        	if(votes.get(user.getId()) == type) {
	        	System.out.print("User already voted");
	        	return;
        	}

        	score = (votes.get(user.getId()) == VoteType.UPVOTE) ? -2 : 2;
        }

		if(score == 0) {
			score = type == VoteType.UPVOTE ? 1 : -1;
		}
		votes.put(this.getId(), type);
		voteCount.addAndGet(score);

		System.out.println("Voted successfully");
	}

	public int getVoteVount() {
		return this.voteCount.get();
	}

}