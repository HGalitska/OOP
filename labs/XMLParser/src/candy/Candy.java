package candy;

import java.util.Vector;

public class Candy implements Comparable<Candy> {
    private int ID = -1;
    private String Type; //restriction on setters
    private String Name;
    private int Energy;
    private Vector<Ingredient> Recipe;
    private NutrValue Value;
    private String Production;

    public int getID() {
        if (ID == -1) {
            System.out.println("No ID for this candy.");
        }
        return ID;
    }

    public void setID(int ID) {
        if (ID > 99 && ID < 1000) this.ID = ID;
        else {
            System.out.println("ID must be 3 digits long.");
            this.ID = -1;
        }
    }

    public String getType() {
        if (this.Type.equals("")) System.out.println("No Type for this candy");
        return Type;
    }

    public void setType(String type) {
        if (type.equals("Chocolate") || type.equals("ChocoFill") || type.equals("Caramel") || type.equals("Iris")) {
        this.Type = type;
        } else System.out.println("Wrong type for candy.");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        if (energy < 0 || energy > 500) System.out.println("Not valid Energy for candy.");
        else this.Energy = energy;
    }

    public Vector<Ingredient> getRecipe() {
        return Recipe;
    }

    //-----------------------------------------_!!!_HOW TO CHECK INGREDIENTS BASED ON TYPE?

    private boolean checkIngredients(Vector<Ingredient> ingredients) {
        //check quantities
        for (Ingredient ingr : ingredients) {
            if (ingr.getQuantity() < 0 || ingr.getQuantity() > 50) {
                System.out.println("Invalid quantity for ingredient.");
                return false;
            }
        }

        //check names
        switch (getType()) {
            case "Caramel":
                if (ingredients.size() != 3) return false;
                if (!ingredients.get(0).getName().equals("water")) return false;
                if (!ingredients.get(1).getName().equals("sugar")) return false;
                if (!ingredients.get(2).getName().equals("vanillin")) return false;
                break;
            case "Iris":
                if (ingredients.size() != 3) return false;
                if (!ingredients.get(0).getName().equals("water")) return false;
                if (!ingredients.get(1).getName().equals("fructose")) return false;
                if (!ingredients.get(2).getName().equals("vanillin")) return false;
                break;
            case "Chocolate":
                if (ingredients.size() != 3) return false;
                if (!ingredients.get(0).getName().equals("water")) return false;
                if (!ingredients.get(1).getName().equals("sugar")) return false;
                if (!ingredients.get(2).getName().equals("chocolate")) return false;
                break;
            case "ChocoFill":
                if (ingredients.size() != 4) return false;
                if (!ingredients.get(0).getName().equals("water")) return false;
                if (!ingredients.get(1).getName().equals("sugar")) return false;
                if (!ingredients.get(2).getName().equals("chocolate")) return false;
                if (!ingredients.get(3).getName().equals("fill")) return false;
                break;
        }
        return true;
    }

    public void setRecipe(Vector<Ingredient> recipe) {
        if (checkIngredients(recipe)) Recipe = recipe;
    }

    public NutrValue getValue() {
        return Value;
    }

    public void setValue(NutrValue value) {
        Value = value;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public Candy() {
    }

    public Candy(int id, String t, String n, int e, Vector<Ingredient> r, NutrValue v, String p) {
        this.ID = id;
        this.Type = t;
        this.Name = n;
        this.Energy = e;
        this.Recipe = r;
        this.Value = v;
        this.Production = p;
    }

    @Override
    public int compareTo(Candy other) {
        if (Energy > other.Energy) return 1;
        else if (Energy < other.Energy) return -1;
        return 0;
    }
}
