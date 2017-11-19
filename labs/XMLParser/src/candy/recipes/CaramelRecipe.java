package candy.recipes;

import java.util.Vector;

public class CaramelRecipe implements Recipe {
    private Ingredient water;
    private Ingredient sugar;
    private Ingredient vanillin;

    @Override
    public void show() {
        System.out.println("Caramel Recipe:");
        System.out.println("  Water: " + water.quantity + " g.");
        System.out.println("  Sugar: " + sugar.quantity + " g.");
        System.out.println("  Vanillin: " + vanillin.quantity + " g.");
    }

    @Override
    public void updateIngredients(Vector<Ingredient> ingredients) {
        if (ingredients.size() != 3) System.out.println("Bad ingredients for caramel candy.");

        for (Ingredient i : ingredients) {
            switch (i.name) {
                case "water":
                    this.water = i;
                    break;
                case "sugar":
                    this.sugar = i;
                    break;
                case "vanillin":
                    this.vanillin = i;
                    break;
                default:
                    System.out.println("Bad ingredient for caramel candy.");
            }
        }
    }
}
