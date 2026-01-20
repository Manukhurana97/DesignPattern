package strategy;

import entities.Rider;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface DeliverPartnerMatchingStrategy {

    Optional<Rider> findRider(ArrayList<Rider> riders);
}
