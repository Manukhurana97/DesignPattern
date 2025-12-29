package observer;

import entities.*;

import java.util.*;

public interface SendNotifications {
    void sendPostNotification(Post post, Set<User> followers);
}