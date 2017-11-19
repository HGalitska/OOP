package candy.recipes;

import java.util.Vector;

public interface Recipe {
    void show();

    void updateIngredients(Vector<Ingredient> ingredients);
}