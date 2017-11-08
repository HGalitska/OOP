package candy;

public class Recipe{
    public int water = 0;
    public int sugar = 0;
    public int fructose = 0;
    public int vanillin = 0;
    public String chocolate = "None";

    public Recipe() {}
    public Recipe(int w, int s, int f, int v, String choc){
        this.water = w;
        this.sugar = s;
        this.fructose = f;
        this.vanillin = v;
        this.chocolate = choc;
    }

    void show(){
        System.out.println("    Water: " + water + "mg.");
        System.out.println("    Sugar: " + sugar + "mg.");
        System.out.println("    Fructose: " + fructose + "mg.");
        System.out.println("    Vanillin: " + vanillin + "mg.");
        System.out.println("    Chocolate: " + chocolate);
    }
}