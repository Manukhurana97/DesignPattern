package service;

import entities.Notification;
import entities.Post;
import entities.User;
import enums.NotificationType;
import observer.OnScreenNotification;
import observer.SendNotifications;
import strategy.ChronologicalSortStrategy;
import strategy.FeedSortingStrategy;

import java.util.*;

public class PostService {
    private static PostService INSTANCE;
    private final Map<String, Post> posts;
    private AccountService accountService;
    private SendNotifications sendNotifications;
    private FeedSortingStrategy feedSortingStrategy;

    public PostService() {
        this.accountService = AccountService.getInstance();
        this.posts = new HashMap<>();
        this.sendNotifications = new OnScreenNotification();
        feedSortingStrategy = new ChronologicalSortStrategy();
    }

    public static synchronized PostService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostService();
        }

        return INSTANCE;
    }

    public Post post(String description, User user) {
        System.out.println(" -> posting...");
        Post post = new Post(description, user);

        // sending notification to followers
        this.sendNotifications.sendPostNotification(post, user.getFollowers());

        this.updatedPost(post);
        user.addNotification(new Notification(("Posted successfully " + description.substring(0, 10) + "..."), NotificationType.POST_CONTENT));
        return post;
    }

    public void postComment(String description, Post post, User user) {
        System.out.println(" -> posting comment ...");
        this.updatedPost(post.setComment(description, user));
        post.getAuthor().addNotification(new Notification((user.getName() + "added comment: " + description.substring(0, 10) + "..." + " on post: " + post.getDescription()), NotificationType.POST_COMMENT));
    }

    public void postLike(Post post, User user) {
        System.out.println(" -> posting like ...");
        this.updatedPost(post.setLikes(user));
        post.getAuthor().addNotification(new Notification((user.getName() + " like your post: " + post.getDescription().substring(0, 10) + "..."), NotificationType.POST_LIKE));
    }

    public List<Post> searchFeed() {
        return this.feedSortingStrategy.sort(new ArrayList<>(posts.values()), new HashSet<>(accountService.getAllAccounts().values()));
    }

    private void updatedPost(Post post) {
        posts.put(post.getId(), post);
    }

}
