package strategy;

import entities.Post;
import entities.User;

import java.util.*;

public interface FeedSortingStrategy {
    List<Post> sort(List<Post> posts, Set<User> followee);
}
