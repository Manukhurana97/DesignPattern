package strategy.pricing;

import entities.Trip;

public class DefaultPriceStrategy implements PricingStrategy{
    public double calculatePrice(Trip trip) {
        return 10;
    }
}
