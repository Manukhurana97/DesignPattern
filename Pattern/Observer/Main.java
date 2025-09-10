import java.util.*;


interface Observer { // subscriber
   void update(String message);
}

interface Subject { // publisher
	void subscribe(Observer observer);
	void unSubscribe(Observer observer);
	void notifyUser(String message);
}

class Youtube implements Subject {
	List<Observer> subscriberList = new ArrayList<>();

	@Override
	public void subscribe(Observer observer) {
		subscriberList.add(observer);
	}

	@Override
	public void unSubscribe(Observer observer) {
		subscriberList.remove(observer);
	}

	@Override
	public void notifyUser(String message) {
		for(Observer ob: subscriberList) {
			ob.update(message);
		}
	} 

	public void uploadVideo(String title) {
		System.out.println("Channel updated new video");
		notifyUser("new Video uploaded "+title);
	}
}

class Subscriber implements Observer{
	
	String name;
	Subscriber(String name) {
		this.name = name;
	}

	@Override
	public void update(String message) {
		System.out.println(message+" : notifying user  " +name);
	}
}


public class Main {

	public static void main(String[] args) {
		Youtube youtube = new Youtube();
		Observer observer1 = new Subscriber("A");
		Observer observer2 = new Subscriber("B");
		Observer observer3 = new Subscriber("C");

		youtube.subscribe(observer1);
		youtube.subscribe(observer2);
		youtube.subscribe(observer3);

		youtube.uploadVideo("Hello");

		youtube.unSubscribe(observer2);

		youtube.uploadVideo("world");

	}

}