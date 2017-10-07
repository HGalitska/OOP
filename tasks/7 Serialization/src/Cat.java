public class Cat  extends java.lang.Object implements java.io.Serializable{
    private String name;
    private int age;
    private String mood;

    Cat(String name, int age, String mood) {
        this.name = name;
        this.age = age;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getMood() {
        return mood;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setMood(String mood){
        this.mood = mood;
    }

    public String sayMeow() {
        return "Name: " + name +"("+age+")" + "\nSays meow in a mood: " + mood;
    }
}
