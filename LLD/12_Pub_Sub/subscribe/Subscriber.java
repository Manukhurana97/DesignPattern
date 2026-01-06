package subscribe;

import entities.Message;

public interface Subscriber {
    String getId();

    void onMessage(Message message);
}