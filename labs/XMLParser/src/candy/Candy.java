package candy;

import candy.recipes.Recipe;

public class Candy implements Comparable<Candy> {
    public int ID;
    public String Type;
    public String Name;
    public int Energy;
    public Recipe recipe;
    public NutrValue Value;
    public String Production;

    public Candy() {
    }

    public Candy(int id, String t, String n, int e, Recipe r, NutrValue v, String p) {
        this.ID = id;
        this.Type = t;
        this.Name = n;
        this.Energy = e;
        this.recipe = r;
        this.Value = v;
        this.Production = p;
    }

    @Override
    public int compareTo(Candy other) {
        if (Energy > other.Energy) return 1;
        else if (Energy < other.Energy) return -1;
        return 0;
    }

    // method to display all the information about candy
    public void show() {
        System.out.println("ID: " + ID);
        System.out.println("Type: " + Type);
        System.out.println("Name: " + Name);
        System.out.println("Energy: " + Energy + "kcal.");
        System.out.println("Ingredients: ");
        this.recipe.show();
        System.out.println("Value: ");
        this.Value.show();
        System.out.println("Production: " + Production);
    }
}
