package xmlWork;

import candy.Candy;
import candy.NutrValue;
import candy.recipes.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class STAXParser {
    private String file;

    STAXParser(String xmlFile) {
        file = xmlFile;
    }

    List<Candy> parse() throws XMLStreamException, FileNotFoundException {
        List<Candy> candies = new ArrayList<>();
        Candy candy = new Candy();
        Ingredient ingredient = new Ingredient();
        Vector<Ingredient> ingredients = new Vector<>();
        NutrValue value = new NutrValue();
        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();

                switch (startElement.getName().getLocalPart()) {
                    case "Candy":
                        candy = new Candy();
                        value = new NutrValue();
                        ingredients.clear();
                        break;
                    case "Name":
                        event = reader.nextEvent();
                        candy.Name = event.asCharacters().getData();
                        break;
                    case "Type":
                        event = reader.nextEvent();
                        candy.Type = event.asCharacters().getData();
                        break;
                    case "ID":
                        event = reader.nextEvent();
                        candy.ID = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Energy":
                        event = reader.nextEvent();
                        candy.Energy = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "water":
                        event = reader.nextEvent();
                        ingredient.name = "water";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "sugar":
                        event = reader.nextEvent();
                        ingredient.name = "sugar";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "fructose":
                        event = reader.nextEvent();
                        ingredient.name = "fructose";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "chocolate":
                        event = reader.nextEvent();
                        ingredient.name = "chocolate";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        Attribute choco = startElement.getAttributeByName(QName.valueOf("chocoType"));
                        if (choco != null) ingredient.chocoType = choco.getValue();
                        break;
                    case "fill":
                        event = reader.nextEvent();
                        ingredient.name = "fill";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        Attribute fill = startElement.getAttributeByName(QName.valueOf("fillType"));
                        if (fill != null) ingredient.fillType = fill.getValue();
                        break;
                    case "vanillin":
                        event = reader.nextEvent();
                        ingredient.name = "vanillin";
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Protein":
                        event = reader.nextEvent();
                        value.Protein = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Fat":
                        event = reader.nextEvent();
                        value.Fat = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Carbohydrate":
                        event = reader.nextEvent();
                        value.Carbohydrate = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Production":
                        event = reader.nextEvent();
                        candy.Production = event.asCharacters().getData();
                        break;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("water")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                else if (endElement.getName().getLocalPart().equals("sugar")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                else if (endElement.getName().getLocalPart().equals("fructose")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                else if (endElement.getName().getLocalPart().equals("chocolate")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                else if (endElement.getName().getLocalPart().equals("fill")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                if (endElement.getName().getLocalPart().equals("vanillin")) {
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }

                else if (endElement.getName().getLocalPart().equals("Candy")) {
                    Recipe recipe = null;
                    switch (candy.Type) {
                        case "Caramel":
                            recipe = new CaramelRecipe();
                            break;
                        case "Chocolate":
                            recipe = new ChocoRecipe();
                            break;
                        case "ChocoFill":
                            recipe = new ChocoFillRecipe();
                            break;
                        case "Iris":
                            recipe = new IrisRecipe();
                            break;
                    }

                    if (recipe != null) {
                        recipe.updateIngredients(ingredients);
                        candy.recipe = recipe;
                        candy.Value = value;
                        candies.add(candy);
                    }

                    ingredients.clear();
                }
            }
        }
        return candies;
    }
}