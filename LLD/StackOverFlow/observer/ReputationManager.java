package observer;

import enums.EventType;
import entity.*;

public class ReputationManager implements PostObserver {
	public static final int QUESTION_UPVOTE_REP = 5;
	public static final int ANSWER_UPVOTE_REP = 10;
	public static final int ACCEPTED_ANSWER_REP = 15;
	public static final int QUESTION_DOWNVOTE_REP = -1;
	public static final int ANSWER_DOWNVOTE_REP = -2;


	public void onPostEvent(Event event) {
		User user = event.getTargetPost().getAuthor();
		switch(event.getType()) {
		
		case UPVOTE_QUESTION:
			user.updateReputation(QUESTION_UPVOTE_REP);
			break;
		case UPVOTE_ANSWER:
			user.updateReputation(ANSWER_UPVOTE_REP);
			break;
		case DOWNVOTE_QUESTION:
			user.updateReputation(QUESTION_DOWNVOTE_REP);
			break;
		case DOWNVOTE_ANSWER:
			user.updateReputation(ANSWER_DOWNVOTE_REP);
			break;
		case ACCEPT_ANSWER:
			user.updateReputation(ACCEPTED_ANSWER_REP);
			break;
		}
	}

}