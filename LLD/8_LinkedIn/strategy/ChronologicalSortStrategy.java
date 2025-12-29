package strategy;

import entities.Post;
import entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ChronologicalSortStrategy implements FeedSortingStrategy{

    public List<Post> sort(List<Post> posts, Set<User> followee) {
        return posts.stream()
                .filter(post -> post.getAuthor().isAccountPublic() || followee.contains(post.getAuthor()))
                .sorted(Comparator.comparing(Post::getPostedAt)).toList();
    }
}
