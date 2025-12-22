package observer;

import entities.*;
import java.util.*;

public class AnswerAndComment implements PostObserver{

	public void notify(Event event) {
		for(var user: event.getUser()) {
			System.out.printf(" -> notifying %s for action %s  on post %s\n",
				user.getId(), event.getEventType(), event.getPost().getId());
		}
	}
}