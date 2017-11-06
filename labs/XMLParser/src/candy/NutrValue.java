package candy;

public class NutrValue {
    private int protein = 0;
    private int fat = 0;
    private int carbohydrate = 0;

    public NutrValue(int p, int f, int c){
            this.protein = p;
            this.fat = f;
            this.carbohydrate = c;
        }

    void show(){
        System.out.println("    Protein: " + protein + "g.");
        System.out.println("    Fat: " + fat + "g.");
        System.out.println("    Carbs: " + carbohydrate + "g.");
    }
}
