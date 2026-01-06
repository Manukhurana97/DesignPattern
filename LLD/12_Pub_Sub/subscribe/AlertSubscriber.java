package subscribe;

import entities.Message;

public class AlertSubscriber implements Subscriber {
    String id;

    public AlertSubscriber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void onMessage(Message message) {
        System.out.printf("!!! [ALERT - %s] : '%s' !!!%n", id, message.getPayload());
    }
}