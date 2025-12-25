package factory;

import enums.Ingredient;

import java.util.Map;

public class Latte extends Coffee {
    public Latte() {
        this.coffeeType = "LATTE";
    }

    public void addCondiments() {
        System.out.println(" -> -> Steaming the milk...");
    }

    public int getPrice() {
        return 250;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.MILK, 2, Ingredient.COFFEE_BEANS, 18, Ingredient.WATER, 10);
    }
}
