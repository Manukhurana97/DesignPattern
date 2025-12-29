import entities.*;
import enums.ConnectionType;
import service.AccountService;
import service.PostService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = AccountService.getInstance();
        PostService postService = PostService.getInstance();

        System.out.println(" --- Create Account ---");
        User user1 = accountService.createAccount("abc", "abc@gmail.com", "abc");
        User user2 = accountService.createAccount("bcd", "bcd@gmail.com", "bcd");
        User user3 = accountService.createAccount("cde", "cde@gmail.com", "cde");
        User user4 = accountService.createAccount("def", "def@gmail.com", "def");
        User user5 = accountService.createAccount("abc", "abc@gmail.com", "abc");

        System.out.println(" --- Follow --- ");
        accountService.follow(user1, user2);
        accountService.updateState(ConnectionType.ACCEPTED, user1, user2);
        accountService.follow(user1, user3);
        accountService.follow(user1, user4);
        accountService.follow(user1, user5);
        accountService.follow(user2, user3);
        accountService.follow(user2, user4);
        accountService.makeAccountPublic(user1);
        accountService.makeAccountPublic(user2);

        System.out.println(" --- Show Notifications ---");
        accountService.viewNotifications(user1);
        accountService.viewNotifications(user1); // no notification

        System.out.print("--- User profile ---");
        accountService.updatedProfile(user1,
                new Profile(
                        "Hi, Im software engineer at apple",
                        List.of(new Education("IIIT", "Batchelor of Technology", "Computer Science", LocalDate.of(2015, 8, 1), LocalDate.of(2019, 7, 1), "A")),
                        List.of(new Job("Apple", "India", "Hyderabad"))
                ));

        System.out.println(" --- Show Notifications ---");
        accountService.viewNotifications(user1);

        System.out.println("--- Post ---");
        Post post = postService.post("Looking for the Job", user1);
        Post post2 = postService.post("Hey there is an opening at apple", user1);

        System.out.println("--- Comment/Like ---");
        postService.postComment("Congratulations", post, user1);
        postService.postLike(post, user1);
        postService.postComment("Congratulations", post, user3);
        postService.postComment("Congratulations", post, user4);
        postService.postComment("Congratulations", post, user5);
        postService.postLike(post, user1);

        System.out.println(" --- Show Notifications ---");
        accountService.viewNotificationsCount(user1);
        accountService.viewNotifications(user1);
        accountService.viewNotifications(user2);


        List<Post> feed = postService.searchFeed();
        for (Post p : feed) {
            System.out.printf("### %s ###\n", p.getDescription());
        }


    }
}