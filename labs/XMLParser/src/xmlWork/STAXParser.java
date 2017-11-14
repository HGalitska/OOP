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

    STAXParser(String xmlFile){
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
        while(reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();

                switch(startElement.getName().getLocalPart()) {
                    case "Candy":
                        candy = new Candy();
                        value = new NutrValue();
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
                    case "name":
                        event = reader.nextEvent();
                        Attribute fill = startElement.getAttributeByName(QName.valueOf("fillType"));
                        Attribute choco = startElement.getAttributeByName(QName.valueOf("chocoType"));
                        if (fill != null) ingredient.fillType = fill.getValue();
                        if (choco != null) ingredient.chocoType = choco.getValue();
                        ingredient.name = event.asCharacters().getData();
                        break;
                    case "quantity":
                        event = reader.nextEvent();
                        ingredient.quantity = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Protein":
                        event = reader.nextEvent();
                        value.protein = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Fat":
                        event = reader.nextEvent();
                        value.fat = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Carbohydrate":
                        event = reader.nextEvent();
                        value.carbohydrate = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Production":
                        event = reader.nextEvent();
                        candy.Production = event.asCharacters().getData();
                        break;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("ingredient")){
                    ingredients.add(ingredient);
                    ingredient = new Ingredient();
                }
                else if (endElement.getName().getLocalPart().equals("Candy")) {
                    Recipe recipe = null;
                    switch(candy.Type){
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
                    recipe.updateIngredients(ingredients);
                    candy.recipe = recipe;
                    candy.Value = value;
                    candies.add(candy);

                    ingredients.clear();
                }
            }
        }
        return candies;
    }
}