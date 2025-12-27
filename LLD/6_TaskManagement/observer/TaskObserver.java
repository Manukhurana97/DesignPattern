package observer;

import entities.User;

public interface TaskObserver {
    void update(String changeDescription, User user);
}