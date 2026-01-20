package strategy.pricing;

import entities.Trip;

public class RatingBasedPriceStrategy implements PricingStrategy{
    public double calculatePrice(Trip trip) {
        return (double) (10 * (1+(double)trip.getDriver().getRating().getValue()/10));
    }
}
