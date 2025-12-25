package service;

import enums.*;
import factory.Coffee;
import factory.CoffeeFactor;
import state.*;

import java.util.*;

public class VendingMachine {
    private static VendingMachine INSTANCE;
    private Coffee selectedCoffee;
    private int remainingAmount;
    private Map<Ingredient, Integer> extras;

    private Account account;
    private VendingMachineState state;
    private CoffeeFactor factor;
    private Inventory inventory;


    public VendingMachine() {
        account = Account.getInstance();
        state = new SelectCoffeeState();
        factor = new CoffeeFactor();
        inventory = Inventory.getInstance();
    }

    public static synchronized VendingMachine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VendingMachine();
        }

        return INSTANCE;
    }
//------------------------------------ user actions ------------------------------------

    public boolean selectCoffee(CoffeeType type, Map<Ingredient, Integer> extras) {
        Coffee coffee = factor.getCoffee(type);
        if (!checkIfIngredientAvailable(coffee, extras)) {
            state = new SelectCoffeeState();
            return false;
        }
        state.selectCoffee(this, coffee, extras);
        return true;
    }

    public void insertMoney(Note note) {
        state.insertMoney(this, note);
    }

    public void dispenceCoffee() {
        state.dispanceCoffee(this);
    }

    private boolean checkIfIngredientAvailable(Coffee coffee, Map<Ingredient, Integer> extras) {
        for (Map.Entry<Ingredient, Integer> k_v : coffee.getRecipe().entrySet()) {
            if (!inventory.doWeHave(k_v.getKey(), k_v.getValue())) return false;
        }

        for (Map.Entry<Ingredient, Integer> k_v : extras.entrySet()) {
            if (!inventory.doWeHave(k_v.getKey(), k_v.getValue())) return false;
        }

        return true;
    }

    //	--------------------------- internal operation of states -------------------------------------
    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void selectCoffeeHelper(Coffee selectedCoffee, Map<Ingredient, Integer> extras) {
        this.selectedCoffee = selectedCoffee;
        this.remainingAmount = selectedCoffee.getPrice();
        this.extras = extras;
    }

    public void insertNoteHelper(Note note) {
        account.insert(note);
        this.remainingAmount -= note.getAmount();
    }

    public int getRemainingAmountHelper() {
        return remainingAmount;
    }

    public void makeCoffeeHelper() {
        this.consumeIngredient();
        selectedCoffee.addCondiments();
        selectedCoffee.prepare();
    }

    public void consumeIngredient() {
        for (Map.Entry<Ingredient, Integer> k_v : selectedCoffee.getRecipe().entrySet()) {
            inventory.consume(k_v.getKey(), k_v.getValue());
        }

        for (Map.Entry<Ingredient, Integer> k_v : extras.entrySet()) {
            inventory.consume(k_v.getKey(), k_v.getValue());
        }
    }

    public void cancelCoffee() {
        this.selectedCoffee = null;
        this.remainingAmount = 0;
        this.extras = null;
    }

    public void cancelWithRefund() {
        this.cancelCoffee();
        System.out.print("Returning " + (selectedCoffee.getPrice() - remainingAmount));
    }

}