package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final String id;
    private final String description;
    private final LocalDateTime timeStamp;
    private final User commentedBy;

    public Comment(String description, User user) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.timeStamp = LocalDateTime.now();
        this.commentedBy = user;
    }
}