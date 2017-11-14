package candy.recipes;

import java.util.Vector;

public class IrisRecipe implements Recipe {
    private Ingredient water;
    private Ingredient fructose;
    private Ingredient vanillin;

    @Override
    public void show() {
        System.out.println("Iris Recipe:");
        System.out.println("  Water: " + water.quantity + " g.");
        System.out.println("  Fructose: " + fructose.quantity + " g.");
        System.out.println("  Vanillin: " + vanillin.quantity + " g.");
    }

    @Override
    public void updateIngredients(Vector<Ingredient> ingredients) {
        if (ingredients.size() != 3) System.out.println("Bad ingredients for iris candy.");

        for (Ingredient i: ingredients) {
            switch (i.name) {
                case "Water":
                    this.water = i;
                    break;
                case "Fructose":
                    this.fructose = i;
                    break;
                case "Vanillin":
                    this.vanillin = i;
                    break;
                default:
                    System.out.println("Bad ingredient for iris candy.");
            }
        }
    }
}