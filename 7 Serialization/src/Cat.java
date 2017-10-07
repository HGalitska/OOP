public class Cat  extends java.lang.Object implements java.io.Serializable{
    public String name;
    public int age;
    public String mood;

    Cat(String name, int age, String mood) {
        this.name = name;
        this.age = age;
        this.mood = mood;
    }
    public void sayMeow() {
        System.out.println("Name: " + name +"("+age+")" + "\nSays meow in a mood: " + mood);
    }
}
