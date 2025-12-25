package factory;

import enums.Ingredient;

import java.util.Map;

public abstract class Coffee {
	protected String coffeeType = "UNKNOWN";

    public String getCoffeeType() {
        return coffeeType;
    }

    public void prepare() {
        System.out.println("\n -> -> Prepare your "+this.getCoffeeType()+ "...");
        grindBean();
        brew();
        pouring();
        System.out.println(" -> -> "+this.getCoffeeType()+" is ready");
    }

    public void grindBean() {
        System.out.println(" -> -> Grinding Bean");
    }

    public void brew() {
        System.out.println(" -> -> Brewing beans.... with hot bean");
    }

    public void pouring() {
        System.out.println(" -> -> Pouring into a cup");
    }

    public abstract void addCondiments();
    public abstract int getPrice();
    public abstract Map<Ingredient, Integer> getRecipe();
}