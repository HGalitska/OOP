package xmlWork;

import candy.Candy;
import candy.Ingredient;
import candy.NutrValue;

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
                        candy.setName(event.asCharacters().getData());
                        break;
                    case "Type":
                        event = reader.nextEvent();
                        candy.setType(event.asCharacters().getData());
                        break;
                    case "ID":
                        event = reader.nextEvent();
                        candy.setID(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "Energy":
                        event = reader.nextEvent();
                        candy.setEnergy(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "water":
                        event = reader.nextEvent();
                        ingredient.setName("water");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "sugar":
                        event = reader.nextEvent();
                        ingredient.setName("sugar");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "fructose":
                        event = reader.nextEvent();
                        ingredient.setName("fructose");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "chocolate":
                        event = reader.nextEvent();
                        ingredient.setName("chocolate");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        Attribute choco = startElement.getAttributeByName(QName.valueOf("chocoType"));
                        if (choco != null) ingredient.setChocoType(choco.getValue());
                        break;
                    case "fill":
                        event = reader.nextEvent();
                        ingredient.setName("water");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        Attribute fill = startElement.getAttributeByName(QName.valueOf("fillType"));
                        if (fill != null) ingredient.setFillType(fill.getValue());
                        break;
                    case "vanillin":
                        event = reader.nextEvent();
                        ingredient.setName("water");
                        ingredient.setQuantity(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "Protein":
                        event = reader.nextEvent();
                        value.setProtein(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "Fat":
                        event = reader.nextEvent();
                        value.setFat(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "Carbohydrate":
                        event = reader.nextEvent();
                        value.setCarbohydrate(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "Production":
                        event = reader.nextEvent();
                        candy.setProduction(event.asCharacters().getData());
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
                    candy.setRecipe(ingredients);
                    candy.setValue(value);
                    candies.add(candy);

                    ingredients.clear();
                }
            }
        }
        return candies;
    }
}