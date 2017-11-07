package candy;

public class Candy implements Comparable<Candy>{
    public int ID;
    public String Type;
    public String Name;
    public int Energy;
    public Recipe Ingredients;
    public NutrValue Value;
    public String Production;

    public Candy() {}

    public Candy(int id, String t, String n, int e, String prod, Recipe ingr, NutrValue value){
        this.ID = id;
        this.Type = t;
        this.Name = n;
        this.Energy = e;
        this.Production = prod;
        this.Ingredients = ingr;
        this.Value = value;
    }

    @Override
    public int compareTo(Candy other) {

        return 0;
    }

    // method to display all the information about candy
    public void show(){
        System.out.println("ID: " + ID);
        System.out.println("Name: " + Name);
        System.out.println("Type: " + Type);
        System.out.println("Energy: " + Energy + "kcal.");
        System.out.println("Production: " + Production);
        System.out.println("Ingredients: ");
        this.Ingredients.show();
        System.out.println("Value: ");
        this.Value.show();
    }
}
