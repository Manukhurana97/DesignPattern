package subscribe;

import entities.Message;

public class NewSubscriber implements Subscriber {
    String id;

    public NewSubscriber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void onMessage(Message message) {
        System.out.printf("[subscriber %s] received message '%s'%n", id, message.getPayload());
    }
}