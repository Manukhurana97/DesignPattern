package factory;

import enums.*;

public class CoffeeFactor {

    public static Coffee getCoffee(CoffeeType type) {

        return switch (type) {
            case ESPRESSO -> new Espresso();
            case LATTE -> new Latte();
            case CAPPUCCINO -> new Cappuccino();
            default -> throw new IllegalArgumentException("Unsupported coffee type " + type);
        };
    }
}
