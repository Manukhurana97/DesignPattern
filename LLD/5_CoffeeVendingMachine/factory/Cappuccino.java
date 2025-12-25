package factory;

import enums.Ingredient;

import java.util.Map;

public class Cappuccino  extends Coffee {

    Cappuccino() {
        this.coffeeType = "Cappuccino";
    }

    public void addCondiments() {
        System.out.println(" -> -> Steaming the milk...");
    }

    public int getPrice() {
        return 250;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.MILK, 5, Ingredient.COFFEE_BEANS, 18, Ingredient.WATER, 10);
    }
}