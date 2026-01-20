import java.time.*;
import java.util.*;
import java.util.concurrent.*;

enum ChannelType {EMAIL, SMS, ON_SCREEN; }

class NotificationMessage {
    private final String id;
    private final String userId;
    private final String title;
    private final String body;
    private final Instant createdAt;

    public NotificationMessage(String userId, String title, String body) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}


class NotificationRecord {
    private final String id;
    private final String messageId;
    private final ChannelType channelType;
    private final boolean success;
    private final String details;
    private final Instant timeStamp;

    public NotificationRecord(String messageId, ChannelType channelType, boolean success, String details) {
        this.id = UUID.randomUUID().toString();
        this.messageId = messageId;
        this.channelType = channelType;
        this.success = success;
        this.details = details;
        this.timeStamp = Instant.now();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "NotificationRecord{" +
                "id='" + id + '\'' +
                ", messageId='" + messageId + '\'' +
                ", channelType=" + channelType +
                ", success=" + success +
                ", details='" + details + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}


interface NotificationRepository {
    void save(NotificationRecord record);
    List<NotificationRecord> getNotifications();
}


class InMemoryNotificationRepository implements NotificationRepository {
    private final List<NotificationRecord> notifications = new CopyOnWriteArrayList<>();

    public void save(NotificationRecord record) {
        notifications.add(record);
    }
    public List<NotificationRecord> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }
}


interface NotificationChannel {
    void send(NotificationMessage message);
}

class EmailChannel implements NotificationChannel {
    public void send(NotificationMessage message) {
        System.out.printf("[Email] -> to=%s title=%s body=%s%n", message.getUserId(), message.getTitle(), message.getBody());
    }
}

class SMSChannel implements NotificationChannel {
    public void send(NotificationMessage message) {
        System.out.printf("[SMS] -> to=%s body=%s%n", message.getUserId(), message.getBody());
    }
}

class OnScreenChannel implements NotificationChannel {
    public void send(NotificationMessage message) {
        System.out.printf("[ON Screen] -> body=%s%n", message.getBody());
    }
}

abstract class NotificationChannelDecorator implements NotificationChannel {
    protected final NotificationChannel channel;

    public NotificationChannelDecorator(NotificationChannel channel) {
        this.channel = channel;
    }

    public void send(NotificationMessage message) {
        this.channel.send(message);
    }
}

class LoggingDecorator extends NotificationChannelDecorator {
    public LoggingDecorator(NotificationChannel channel) {
        super(channel);
    }

    public void send(NotificationMessage message) {
        System.out.printf("[LOG] Sending on %s message=%s%n", channel.getClass().getSimpleName(), message.getId());
        super.send(message);
        System.out.printf("[LOG] Send on %s message=%s%n", channel.getClass().getSimpleName(), message.getId());
    }
}


class NotificationService {
    private final ConcurrentHashMap<ChannelType, NotificationChannel> registry = new ConcurrentHashMap<>();
    private final NotificationRepository notificationRepository;
    private final ExecutorService executorService;

    public NotificationService() {
        this.notificationRepository = new InMemoryNotificationRepository();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void registerChannel(ChannelType type, NotificationChannel channel) {
        registry.put(type, channel);
    }

    public Map<ChannelType, Boolean> send(NotificationMessage notificationMessage, Set<ChannelType> channels) throws ExecutionException, InterruptedException {
        Map<ChannelType, Boolean> result = new HashMap<>();

        for(ChannelType type: channels) {
            NotificationChannel channel = registry.get(type);

            if(channel == null) {
                NotificationRecord record = new NotificationRecord(notificationMessage.getId(), type, false, "channel-not-registered");
                notificationRepository.save(record);
                result.put(type, false);
                continue;
            }

            Future<Boolean> future = executorService.submit(() -> {
                try {
                    channel.send(notificationMessage);
                    notificationRepository.save(new NotificationRecord(notificationMessage.getId(), type, true, "SENT"));
                    return true;
                } catch (Exception e) {
                    NotificationRecord record = new NotificationRecord(notificationMessage.getId(), type, false, "error submitting event");
                    notificationRepository.save(record);
                    return false;
                }

            });
             result.put(type, future.get());
        }

        return result;
    }

    public Map<ChannelType, Boolean> sendAll(NotificationMessage notificationMessage) {
        return send(notificationMessage, registry.keySet());
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.registerChannel(ChannelType.EMAIL, new LoggingDecorator(new EmailChannel()));
        notificationService.registerChannel(ChannelType.SMS, new SMSChannel());

        notificationService.sendAll(new NotificationMessage("USER_1", "TITLE_1", "BODY_1"));
    }
}