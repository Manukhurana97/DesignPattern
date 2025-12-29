package entities;

import java.time.LocalDateTime;
import java.util.*;

public class Post {
    private final String id;
    private User author;
    private String description;
    private final LocalDateTime postedAt;
    private final Map<String, Comment> comments;
    private final Set<String> likes;

    public Post(String description, User user) {
        this.id = UUID.randomUUID().toString();
        this.author = user;
        this.description = description;
        this.postedAt = LocalDateTime.now();
        this.comments = new HashMap<>();
        this.likes = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public Post setComment(String description, User user) {
        Comment comment = new Comment(description, user);
        comments.put(user.getId(), comment);
        return this;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public Post setLikes(User user) {
        likes.add(user.getId());
        return this;
    }
}
