package factory;

import java.util.*;

import enums.Ingredient;

public class Espresso extends Coffee  {

    Espresso() {
        this.coffeeType = "Espresso";
    }

    public void addCondiments() {
        System.out.println(" -> -> Steaming the water...");
    }

    public int getPrice() {
        return 250;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.COFFEE_BEANS, 18, Ingredient.WATER, 10);
    }
}
