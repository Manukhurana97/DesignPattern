package entity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
	private final String id;
	private final String userName;
	private final AtomicInteger reputation;

	public User(String userName) {
		this.id = UUID.randomUUID().toString();
		this.userName = userName;
		this.reputation = new AtomicInteger(0);
	}

	public void updateReputation(int change) {
		this.reputation.addAndGet(change);
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public int getReputation() {
		return reputation.get();
	}

}