import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;


enum DeviceType {ANDROID, IOS, WEB}

class Device {
    volatile boolean isActive;
    private final String deviceId;
    private final DeviceType type;

    public Device( DeviceType type) {
        this.deviceId = UUID.randomUUID().toString();
        this.type = type;
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getTypeName() {
        return this.type.name();
    }

    public void sendMessage(Message m) {
        System.out.printf("[Deliver] to device=%s type=%s conversationId=%s sequenceId=%s from=%s body=%s %n ", deviceId, type, m.conversationId(), m.sequenceId(), m.fromId(), m.body());
    }
}

class User {
    private final String userId;
    private final String userName;
    private final Set<Device> devices;

    public User(String userName, DeviceType type) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.devices = new CopyOnWriteArraySet<>();
        this.devices.add(new Device(type));
    }



    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }
}

class Group {
    private final String groupId;
    private final String groupName;
    private final Set<User> users = ConcurrentHashMap.newKeySet()  ;

    public Group(String groupName, Set<User> user) {
        this.groupId = UUID.randomUUID().toString();
        this.groupName = groupName;
        users.addAll(user);
    }


    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<User> getUsers(String groupId) {
        return users;
    }

}

record Message(String messageId, String conversationId, long sequenceId, User fromId, Set<User> toIds, String body, Long timeStamp) { }


/* strategy */
interface Repository {
    void saveMessage(Message message);
}


class InMemoryRepository implements Repository {

    private final Map<String, List<Message>> conversation = new ConcurrentHashMap<>();

    public void saveMessage(Message message) {
        conversation.computeIfAbsent(message.conversationId(), k -> Collections.synchronizedList(new ArrayList<>())).add(message);
    }

    public List<Message> getConversation(String conversationId) {
        return conversation.getOrDefault(conversationId, Collections.emptyList());
    }
}


/* singleton */
class UserService {
    private static UserService INSTANCE;
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Group> groups = new ConcurrentHashMap<>();

    public UserService() {
    }


    public synchronized static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }

        return INSTANCE;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public User createUser(String userName, DeviceType type) {
        User user = new User(userName, type);
        users.put(user.getUserId(), user);
        return user;
    }

    public void addDevice(User user, DeviceType deviceType) {
        if(!users.containsKey(user.getUserId())) {
            System.out.println("Invalid userId");
            return;
        }

        users.get(user.getUserId()).getDevices().add(new Device(deviceType));
    }

    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }

    public Group createGroup(String name, Set<User> users) {
        Group group = new Group(name, users);
        groups.put(group.getGroupId(), group);
        return group;

    }
}

/* singleton */
class MessageService {
    private static MessageService INSTANCE;
    private Repository repo;
    private final Map<String, AtomicLong> sequenceMap = new ConcurrentHashMap<>();

    public MessageService() {
        repo = new InMemoryRepository();
    }

    public synchronized static MessageService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageService();
        }

        return INSTANCE;
    }

    public String oneToOneConversation(String fromUserId, String toUserId) {
        return (fromUserId.compareTo(toUserId) <= 0) ? fromUserId + ":" + toUserId
                : toUserId + ":" + fromUserId;

    }

    public Message sendtoUser(User from, User to, String body) {
        String conv = oneToOneConversation(from.toString(), to.toString());
        return persistAndSend(conv, from, Set.of(to), body);
    }

    public Message sendToGroup(User from, Group group, String body) {
        Set<User> users = group.getUsers(group.getGroupId());
        users.remove(from);
        return persistAndSend(group.getGroupId(), from, users, body);
    }

    public Message persistAndSend(String conversation, User from, Set<User> to, String body) {
        long seq = sequenceMap.computeIfAbsent(conversation, k -> new AtomicLong(0)).incrementAndGet();
        Message m = new Message(UUID.randomUUID().toString(), conversation, seq, from, to, body, System.currentTimeMillis());
        repo.saveMessage(m);

        deliver(m);
        return m;
    }


    public void deliver(Message m) {
        for (User user : m.toIds()) {
            if (user.getDevices().isEmpty()) {
                System.out.printf("User=%s has not active device %s%n", user, 0);
                continue;
            } else {
                for (Device device : user.getDevices()) {
                    if (!device.isActive()) {
                        System.out.printf("[INFO] User device %s is currently offline", device.getTypeName());
                    } else {
                        device.sendMessage(m);
                    }
                }
            }
        }
    }
}


/* facade */
class WhatsApp {
    private final UserService userService;
    private final MessageService messageService;

    public WhatsApp() {
        this.userService = UserService.getInstance();
        this.messageService = MessageService.getInstance();
    }

    public User createUser(String userName, DeviceType deviceType) {
        User user = userService.createUser(userName, deviceType);
        System.out.printf("%s account created and %d added %n", userName, user.getDevices().size());
        return user;
    }

    public void addDevice(User user, DeviceType deviceType) {
        userService.addDevice(user, deviceType);
        System.out.printf("%s added %n",deviceType.name());
    }

    public Group createGroup(String groupName, Set<User> users) {
        Group group = userService.createGroup(groupName, users);
        System.out.printf("Group create %s and %d users added %n", groupName, users.size());
        return group;
    }

    public Message sendMessages(String from, String to, String body) {
        Message message = messageService.sendtoUser(userService.getUser(from), userService.getUser(to), body);
        return message;
    }

    public Message sendToGroup(String from, Group group, String body) {
        Message message = messageService.sendToGroup(userService.getUser(from), group, body);
        System.out.printf("Message send to %s %n", group.getGroupName());
        return message;
    }

}

public class Main {
    public static void main(String[] args) {
        WhatsApp whatsApp = new WhatsApp();
        User user_1 = whatsApp.createUser("User_1", DeviceType.ANDROID);
        whatsApp.addDevice(user_1, DeviceType.WEB);

        User user_2 = whatsApp.createUser("User_2", DeviceType.ANDROID);
        System.out.println();

        whatsApp.sendMessages(user_1.getUserId(), user_2.getUserId(), "Ola!\nw where are you");
        whatsApp.sendMessages(user_2.getUserId(), user_1.getUserId(), "Ola!\nw at home");

        System.out.println();

        User user_3 = whatsApp.createUser("User_3", DeviceType.ANDROID);
        User user_4 = whatsApp.createUser("User_4", DeviceType.IOS);
        User user_5 = whatsApp.createUser("User_5", DeviceType.ANDROID);
        System.out.println();

        Group group = whatsApp.createGroup("BASHOSH", Set.of(user_1, user_2, user_3, user_4, user_5));
        whatsApp.sendToGroup(user_1.getUserId(), group, "Ram ram");
        System.out.println();

    }
}