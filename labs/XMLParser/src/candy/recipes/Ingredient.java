package candy.recipes;

public class Ingredient {
    public String name;
    public int quantity;
    public String fillType = "";
    public String chocoType = "";

    public Ingredient() {
    }

    public Ingredient(String name, int quantity, String chocoType, String fillType) {
        this.name = name;
        this.quantity = quantity;
        this.fillType = fillType;
        this.chocoType = chocoType;
    }
}
