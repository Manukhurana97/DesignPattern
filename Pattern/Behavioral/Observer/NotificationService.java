import java.util.*;

public class NotificationService implements Subject {

	private String message;
	private List<Observer> observers = new ArrayList<>();

	public void sendMessage(String message) {
		this.message = message;
		notifyObserver();
	} 

	public String getMessage() {
		return message;
	}

	public void register(Observer observer) {
		observers.add(observer);
	}

	public void unregister(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObserver() {
		for(Observer observer: observers)
			observer.update(this); // pull model
	}
}