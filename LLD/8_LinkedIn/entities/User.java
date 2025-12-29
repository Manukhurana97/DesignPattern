package entities;

import enums.ConnectionType;
import enums.NotificationType;

import java.util.*;
import java.util.stream.Collectors;

public class User {
    private final String userId;
    private String name;
    private String email;
    private String password;
    private Profile profile;
    private Map<String, Set<User>> followers;
    private Set<User> followeee;
    private List<Notification> notifications;
    private ConnectionType type;
    private boolean isPublic;

    public User(String name, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        followers = new HashMap<>();
        notifications = new ArrayList<>();
        this.type = ConnectionType.PENDING;
        this.isPublic = false;
    }

    public User(String name, String email, String password, Profile profile) {
        this(name, email, password);
        this.profile = profile;
    }


    public String getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return name;
    }


    public Set<User> getFollowers() {
        return followers.getOrDefault(ConnectionType.ACCEPTED, new HashSet<>());
    }

    public User addFollower(User user) {
        if (Objects.equals(this.getId(), user.getId())) {
            System.out.println("you cant follow yourself");
            return user;
        }

        return updateState(type, user);
    }

    public User updateState(ConnectionType type, User user) {
        if (!type.status.equals("PENDING") && followers.containsKey("PENDING") && !followers.get("PENDING").isEmpty()) {
            followers.get("PENDING").remove(user);
        }
        followers.computeIfAbsent(type.status, k -> new HashSet<>()).add(user);
        if (!type.followerMgs.isEmpty())
            addNotification(new Notification(type.followerMgs, NotificationType.FOLLOW));
        if (!type.followeeMgs.isEmpty())
            addNotification(new Notification(type.followeeMgs, NotificationType.FOLLOW));
        return this;
    }

    public boolean isAccountPublic() {
        return isPublic;
    }

    public User makeAccountPublic() {
        this.isPublic = true;
        this.type = ConnectionType.ACCEPTED;
        if (followers.containsKey("PENDING")) {
            followers.get("PENDING").forEach(
                    follower -> {
                        followers.computeIfAbsent(this.type.status, k -> new HashSet<>()).add(follower);
                        follower.addNotification(new Notification(this.name + " accepted your connection", NotificationType.FOLLOW));
                    });

            followers.remove("PENDING");
        }
        return this;
    }

    public User makeAccountPrivate() {
        this.isPublic = false;
        this.type = ConnectionType.PENDING;

        return this;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public User viewNotifications() {
        this.updateNotification(notifications.stream()
                .map(notification -> {
                    if (!notification.isRead()) {
                        System.out.printf(" => Notification: [%s] - [%s] at %s \n", notification.getCreatedAt(), this.name, notification.getDescription());
                        notification.markAsRead();
                    }
                    return notification;
                }).collect(Collectors.toList()));
        return this;
    }

    public void viewNotificationsCount() {
        Map<NotificationType, Long> countMap = notifications.stream()
                .filter(notification -> !notification.isRead())
                .collect(Collectors.groupingBy(Notification::getType, Collectors.counting()));

        if (countMap.isEmpty()) {
            System.out.println("No notification found");
            return;
        }
        for (Map.Entry<NotificationType, Long> k_v : countMap.entrySet()) {
            System.out.println(" ->[" + k_v.getKey() + "]: " + k_v.getValue());
        }
    }

    private User updateNotification(List<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public User updatedProfile(Profile profile) {
        this.profile = profile;
        addNotification(new Notification("Profile updated successfully", NotificationType.PROFILE_UPDATED));
        return this;
    }

}