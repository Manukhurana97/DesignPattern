package service;

import entities.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiderManager {
    public static RiderManager INSTANCE;
    private Map<String, Rider> riders = new ConcurrentHashMap<>();

    public static synchronized RiderManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RiderManager();
        }

        return INSTANCE;
    }

    public Rider registerRider(String name, String phoneNumber) {
        Rider rider = new Rider(name, phoneNumber);
        riders.put(rider.getId(), rider);
        System.out.println("Account Created");
        return rider;
    }
}
