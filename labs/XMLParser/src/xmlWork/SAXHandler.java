package xmlWork;

import candy.NutrValue;
import candy.recipes.*;
import candy.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.Vector;

public class SAXHandler extends DefaultHandler {
    private List<Candy> candies; // container array, initialized in constructor
    private Vector<Ingredient> ingredientsVector = new Vector<>();

    private Candy currentCandy = new Candy();
    private Ingredient currentIngredient = new Ingredient();
    private NutrValue currentValue = new NutrValue();

    private boolean id = false;
    private boolean type = false;
    private boolean name = false;
    private boolean energy = false;
    private boolean ingredName = false;
    private boolean ingredQuantity = false;
    private boolean protein = false;
    private boolean fat = false;
    private boolean carbohydrate = false;
    private boolean production = false;

    SAXHandler(List<Candy> container){
        this.candies = container;
    }

    private void reset(){
        id = false;
        type = false;
        name = false;
        energy = false;
        ingredName = false;
        ingredQuantity = false;
        protein = false;
        fat = false;
        carbohydrate = false;
        production = false;

    }

    //listener for start elements in xml file
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Candy")) {
            currentCandy = new Candy();
            ingredientsVector.clear();
            currentValue = new NutrValue();
        }

        if (qName.equalsIgnoreCase("ID")) {
            id  = true;
        }
        else if (qName.equals("Name")) {
            name = true;
        }
        else if (qName.equalsIgnoreCase("TYPE")) {
            type = true;
        }
        else if (qName.equalsIgnoreCase("ENERGY")) {
            energy = true;
        }
        else if (qName.equals("ingredient")){
            currentIngredient = new Ingredient();
        }
        else if (qName.equals("name")) {
            ingredName = true;
            currentIngredient.fillType = attributes.getValue("fillType");
            currentIngredient.chocoType = attributes.getValue("chocoType");
        }
        else if (qName.equalsIgnoreCase("QUANTITY")) {
            ingredQuantity = true;
        }
        else if (qName.equalsIgnoreCase("PROTEIN")) {
            protein = true;
        }
        else if (qName.equalsIgnoreCase("FAT")) {
            fat = true;
        }
        else if (qName.equalsIgnoreCase("CARBOHYDRATE")) {
            carbohydrate = true;
        }
        else if (qName.equalsIgnoreCase("PRODUCTION")) {
            production  = true;
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if(qName.equals("ingredient")){
            ingredientsVector.add(currentIngredient);
        }

        if(qName.equals("Candy")){          // if closing </Candy> element is encountered
            Recipe recipe = null;
            switch (currentCandy.Type){
                case "Caramel":
                    recipe = new CaramelRecipe();
                    break;
                case "Iris":
                    recipe = new IrisRecipe();
                    break;
                case "Chocolate":
                    recipe = new ChocoRecipe();
                    break;
                case "ChocoFill":
                    recipe = new ChocoFillRecipe();
                    break;

            }
            recipe.updateIngredients(ingredientsVector);
            currentCandy.recipe = recipe;
            currentCandy.Value = currentValue;

            // add new candy to candy list
            this.candies.add(currentCandy);

            reset(); //reset all candy properties to initial values
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if (id) {
            currentCandy.ID = Integer.parseInt(new String(ch, start, length));
            id = false;
        }
        else if (type) {
            currentCandy.Type = new String(ch, start, length);
            type = false;
        }
        else if (name) {
            currentCandy.Name = new String(ch, start, length);
            name = false;
        }
        else if (energy) {
            currentCandy.Energy = Integer.parseInt(new String(ch, start, length));
            energy = false;
        }
        else if (ingredName) {
            currentIngredient.name = new String(ch, start, length);
            ingredName = false;
        }
        else if (ingredQuantity) {
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            ingredQuantity = false;
        }
        else if (protein) {
            currentValue.protein = Integer.parseInt(new String(ch, start, length));
            protein = false;
        }
        else if (fat) {
            currentValue.fat = Integer.parseInt(new String(ch, start, length));
            fat = false;
        }
        else if (carbohydrate) {
            currentValue.carbohydrate = Integer.parseInt(new String(ch, start, length));
            carbohydrate = false;
        }
        else if (production) {
            currentCandy.Production = new String(ch, start, length);
            production = false;
        }


    }
}
