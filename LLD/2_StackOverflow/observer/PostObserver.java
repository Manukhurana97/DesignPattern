package observer;

import entities.*;

public interface PostObserver {
	void notify(Event event);
}