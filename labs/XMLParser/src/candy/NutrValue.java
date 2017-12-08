package candy;

public class NutrValue {
    private int Protein = 0;
    private int Fat = 0;
    private int Carbohydrate = 0;

    public int getProtein() {
        return Protein;
    }

    public void setProtein(int protein) {
        if (protein >= 0 && protein < 10) {
            Protein = protein;
        }
    }

    public int getFat() {
        return Fat;
    }

    public void setFat(int fat) {
        if (fat >= 0 && fat < 10) {
            Fat = fat;
        }
    }

    public int getCarbohydrate() {
        return Carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        if (carbohydrate >= 0 && carbohydrate < 10) {
            Carbohydrate = carbohydrate;
        }
    }

    public NutrValue() {
    }

    public NutrValue(int p, int f, int c) {
        if (p >= 0 && p < 10 && f >= 0 && f < 10 && c >= 0 && c < 10) {
            this.Protein = p;
            this.Fat = f;
            this.Carbohydrate = c;
        }
    }
}
