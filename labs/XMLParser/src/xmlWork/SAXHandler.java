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
    private String currentRecipe;

    private boolean id = false;
    private boolean type = false;
    private boolean name = false;
    private boolean energy = false;

    private boolean water = false;
    private boolean sugar = false;
    private boolean fructose = false;
    private boolean chocolate = false;
    private boolean fill = false;
    private boolean vanillin = false;

    private boolean protein = false;
    private boolean fat = false;
    private boolean carbohydrate = false;

    private boolean production = false;

    SAXHandler(List<Candy> container) {
        this.candies = container;
    }

    private void reset() {
        id = false;
        type = false;
        name = false;
        energy = false;

        water = false;
        sugar = false;
        fructose = false;
        chocolate = false;
        fill = false;
        vanillin = false;

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
            currentValue = new NutrValue();
            currentIngredient = new Ingredient();
            currentRecipe = "";

            ingredientsVector.clear();
            reset();
        }

        if (qName.equalsIgnoreCase("ID")) {
            id = true;
        } else if (qName.equalsIgnoreCase("NAME")) {
            name = true;
        } else if (qName.equalsIgnoreCase("TYPE")) {
            type = true;
        } else if (qName.equalsIgnoreCase("ENERGY")) {
            energy = true;
        }

        else if (qName.equalsIgnoreCase("CARAMELCANDY")) {
            currentRecipe = "Caramel";
        } else if (qName.equalsIgnoreCase("IRISCANDY")) {
            currentRecipe = "Iris";
        } else if (qName.equalsIgnoreCase("CHOCOLATECANDY")) {
            currentRecipe = "Chocolate";
        } else if (qName.equalsIgnoreCase("CHOCOFILLCANDY")) {
            currentRecipe = "ChocoFill";
        }

        else if (qName.equalsIgnoreCase("WATER")) {
            water = true;
        } else if (qName.equalsIgnoreCase("SUGAR")) {
            sugar = true;
        } else if (qName.equalsIgnoreCase("FRUCTOSE")) {
            fructose = true;
        } else if (qName.equalsIgnoreCase("CHOCOLATE")) {
            chocolate = true;
            currentIngredient.fillType = attributes.getValue("chocoType");
        } else if (qName.equalsIgnoreCase("FILL")) {
            fill = true;
            currentIngredient.fillType = attributes.getValue("fillType");
        } else if (qName.equalsIgnoreCase("VANILLIN")) {
            vanillin = true;
        }

        else if (qName.equalsIgnoreCase("PROTEIN")) {
            protein = true;
        } else if (qName.equalsIgnoreCase("FAT")) {
            fat = true;
        } else if (qName.equalsIgnoreCase("CARBOHYDRATE")) {
            carbohydrate = true;
        }

        else if (qName.equalsIgnoreCase("PRODUCTION")) {
            production = true;
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (qName.equalsIgnoreCase("water")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }
        else if (qName.equalsIgnoreCase("sugar")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }
        else if (qName.equalsIgnoreCase("fructose")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }
        else if (qName.equalsIgnoreCase("chocolate")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }
        else if (qName.equalsIgnoreCase("fill")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }
        else if (qName.equalsIgnoreCase("vanillin")) {
            ingredientsVector.add(currentIngredient);
            currentIngredient = new Ingredient();
        }

        else if (qName.equals("Candy")) {          // if closing </Candy> element is encountered
            Recipe recipe = null;
            switch (currentRecipe) {
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
            if (recipe != null) {
                recipe.updateIngredients(ingredientsVector);
                currentCandy.recipe = recipe;
                currentCandy.Value = currentValue;

                this.candies.add(currentCandy);
            }
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if (id) {
            currentCandy.ID = Integer.parseInt(new String(ch, start, length));
            id = false;
        } else if (type) {
            currentCandy.Type = new String(ch, start, length);
            type = false;
        } else if (name) {
            currentCandy.Name = new String(ch, start, length);
            name = false;
        } else if (energy) {
            currentCandy.Energy = Integer.parseInt(new String(ch, start, length));
            energy = false;
        }

        else if (water) {
            currentIngredient.name = "water";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            water = false;
        } else if (sugar) {
            currentIngredient.name = "sugar";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            sugar = false;
        }else if (fructose) {
            currentIngredient.name = "fructose";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            fructose = false;
        }else if (chocolate) {
            currentIngredient.name = "chocolate";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            chocolate = false;
        }else if (fill) {
            currentIngredient.name = "fill";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            fill = false;
        }else if (vanillin) {
            currentIngredient.name = "vanillin";
            currentIngredient.quantity = Integer.parseInt(new String(ch, start, length));
            vanillin = false;
        }

        else if (protein) {
            currentValue.Protein = Integer.parseInt(new String(ch, start, length));
            protein = false;
        } else if (fat) {
            currentValue.Fat = Integer.parseInt(new String(ch, start, length));
            fat = false;
        } else if (carbohydrate) {
            currentValue.Carbohydrate = Integer.parseInt(new String(ch, start, length));
            carbohydrate = false;
        }

        else if (production) {
            currentCandy.Production = new String(ch, start, length);
            production = false;
        }
    }
}
