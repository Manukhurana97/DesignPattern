package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final String id;
    private String description;
    private User author;
    private final LocalDateTime postedAt;

    public Comment(String description, User author) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.author = author;
        this.postedAt = LocalDateTime.now();
    }
}
