package strategy.pricing;

import entities.*;

public interface PricingStrategy {
    double calculatePrice(Trip trip);
}
