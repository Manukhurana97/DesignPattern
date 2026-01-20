package strategy;

import entities.Rider;

import java.util.ArrayList;
import java.util.Optional;

public class NearestAvailableStrategy implements DeliverPartnerMatchingStrategy{

    @Override
    public Optional<Rider> findRider(ArrayList<Rider> riders) {
        return riders.stream().filter(Rider::getIsAvailable).findFirst();
    }
}
