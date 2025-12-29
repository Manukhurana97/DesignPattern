package observer;

import entities.Notification;
import entities.Post;
import entities.User;
import enums.NotificationType;

import java.util.List;
import java.util.Set;

public class OnScreenNotification implements SendNotifications{


    public void sendPostNotification(Post post, Set<User> followers) {
        followers.forEach(user -> {
            user.addNotification(new Notification(post.getAuthor().getName() + " has posted new post "+post.getDescription().substring(0, 10)+"...", NotificationType.POST_CONTENT));
        });
    }
}
