package entity;

import java.util.*;

public class Answer extends Post {
	 private boolean isAccepted = false;

	public Answer(User author, String body) {
		super(UUID.randomUUID().toString(), author, body);
	}

	
    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isAccepted() { return isAccepted; }
}