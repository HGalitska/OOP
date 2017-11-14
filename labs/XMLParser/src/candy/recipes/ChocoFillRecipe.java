package candy.recipes;

import java.util.Vector;

public class ChocoFillRecipe implements Recipe {
    private Ingredient water;
    private Ingredient sugar;
    private Ingredient chocolate;
    private Ingredient fill;

    @Override
    public void show() {
        System.out.println("Chocolate candy recipe:");
        System.out.println("  Water: " + water.quantity + " g.");
        System.out.println("  Sugar: " + sugar.quantity + " g.");
        System.out.println("  Chocolate: " + chocolate.quantity + " g. " + chocolate.chocoType);
        System.out.println("  Fill: " + fill.quantity + "g. " + fill.fillType);
    }

    @Override
    public void updateIngredients(Vector<Ingredient> ingredients) {
        if (ingredients.size() != 4) System.out.println("Bad ingredients for chocolate candy with fill.");

        for (Ingredient i: ingredients) {
            switch (i.name) {
                case "Water":
                    this.water = i;
                    break;
                case "Sugar":
                    this.sugar = i;
                    break;
                case "Chocolate":
                    this.chocolate = i;
                    break;
                case "Fill":
                    this.fill = i;
                    break;
                default:
                    System.out.println("Bad ingredient for chocolate candy with fill.");
            }
        }
    }
}