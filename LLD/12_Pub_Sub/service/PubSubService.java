package service;

import entities.Message;
import entities.Topic;
import subscribe.Subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubSubService {
    private static PubSubService INSTANCE;
    private final ExecutorService executorService;
    private final Map<String, Topic> topics;

    public PubSubService() {
        this.executorService = Executors.newCachedThreadPool();
        this.topics = new ConcurrentHashMap<>();
    }


    public synchronized static PubSubService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PubSubService();
        }
        return INSTANCE;
    }


    public void createTopic(String topicName) {
        topics.computeIfAbsent(topicName, k -> new Topic(topicName, executorService));
        System.out.println("Topic " + topicName + " created");
    }


    public void subscribe(String topicName, Subscriber subscribe) {
        Topic topic = topics.get(topicName);

        if (topic == null) {
            throw new IllegalArgumentException("Topic not fount " + topicName);
        }

        topic.addSubscriber(subscribe);
        System.out.println("Subscribe '" + subscribe.getId() + "' subscribe to topic " + topicName);
    }


    public void unSubscribe(String topicName, Subscriber subscribe) {
        Topic topic = topics.get(topicName);

        if (topic == null) {
            throw new IllegalArgumentException("Topic not fount " + topicName);
        }

        topic.addSubscriber(subscribe);
        System.out.println("Subscribe '" + subscribe.getId() + "' unsubscribe to topic " + topicName);
    }


    public void publish(String topicName, Message message) {
        System.out.println("Publishing message to topic: " + topicName);
        Topic topic = topics.get(topicName);

        if (topic == null) {
            throw new IllegalArgumentException("Topic not fount " + topicName);
        }

        topic.broadCast(message);
    }

    public void shutDown() {
        System.out.println("PubSub Service shutting down...");
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executorService.shutdown();
            Thread.currentThread().interrupt();
        }
    }
}