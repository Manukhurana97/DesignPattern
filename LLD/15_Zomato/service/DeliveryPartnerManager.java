package service;

import entities.Rider;
import strategy.DeliverPartnerMatchingStrategy;
import strategy.NearestAvailableStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DeliveryPartnerManager {
    private static DeliveryPartnerManager INSTANCE;
    private final DeliverPartnerMatchingStrategy deliverPartnerMatchingStrategy;
    private final Map<String, Rider> riders;

    public DeliveryPartnerManager() {
        riders = new ConcurrentHashMap<>();
        this.deliverPartnerMatchingStrategy = new NearestAvailableStrategy();
    }

    public synchronized static DeliveryPartnerManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DeliveryPartnerManager();
        }

        return INSTANCE;
    }

    public Optional<Rider> findDeliveryPartner() {
        synchronized (this) {
            Optional<Rider> rider = deliverPartnerMatchingStrategy.findRider(new ArrayList<>(riders.values()));

            rider.ifPresent(value -> value.setIsAvailable(false));
            return rider;
        }
    }

    public Rider addRider(String name, String vehicleNumber, String phoneNumber) {
        Rider rider = new Rider(name, vehicleNumber, phoneNumber);

        riders.put(rider.getId(), rider);
        System.out.printf("%s rider onboarded successfully \n", name);
        return rider;
    }
}
