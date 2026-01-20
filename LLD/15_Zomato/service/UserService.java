package service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import entities.*;

public class UserService {
    private static UserService INSTANCE;
    private Map<String,User> users = new ConcurrentHashMap<>();

    public synchronized static UserService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public boolean validAccount(String userId) {
        return users.containsKey(userId);
    }

    public User getUserId(String userId) { return users.get(userId);}

    public User createAccount(String name, String mobileNumber, String address) {
        User user = new User(name, mobileNumber, address);
        users.put(user.getId(), user);
        System.out.println("--- Account Created ---");
        return user;
    }
}
