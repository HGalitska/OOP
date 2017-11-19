package candy;

public class NutrValue {
    public int Protein = 0;
    public int Fat = 0;
    public int Carbohydrate = 0;

    public NutrValue() {
    }

    public NutrValue(int p, int f, int c) {
        this.Protein = p;
        this.Fat = f;
        this.Carbohydrate = c;
    }

    void show() {
        System.out.println("    Protein: " + Protein + "g.");
        System.out.println("    Fat: " + Fat + "g.");
        System.out.println("    Carbs: " + Carbohydrate + "g.");
    }
}
