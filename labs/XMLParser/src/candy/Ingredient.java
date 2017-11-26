package candy;

public class Ingredient {
    private String name;
    private int quantity;
    private String fillType = "";
    private String chocoType = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 || quantity > 50) {
            System.out.println("Bad quantity for ingredient.");
            return;
        }
        this.quantity = quantity;
    }

    public String getFillType() {
        return fillType;
    }

    public void setFillType(String fillType) {
        this.fillType = fillType;
    }

    public String getChocoType() {
        return chocoType;
    }

    public void setChocoType(String chocoType) {
        this.chocoType = chocoType;
    }

    public Ingredient() {
    }

    public Ingredient(String name, int quantity, String chocoType, String fillType) {
        if (quantity < 1 || quantity > 50) {
            System.out.println("Bad quantity for ingredient.");
            return;
        }
        this.name = name;
        this.quantity = quantity;
        this.fillType = fillType;
        this.chocoType = chocoType;
    }
}
