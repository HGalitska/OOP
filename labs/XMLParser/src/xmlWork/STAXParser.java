package xmlWork;

import candy.Candy;
import candy.NutrValue;
import candy.Recipe;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class STAXParser {
    private String file;

    STAXParser(String xmlFile){
        file = xmlFile;
    }

    public List<Candy> parse() throws XMLStreamException, FileNotFoundException {
        List<Candy> candies = new ArrayList<>();
        Candy candy = null;
        Recipe ingredients = null;
        NutrValue value = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
        while(reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();

                switch(startElement.getName().getLocalPart()) {
                    case "Candy":
                        candy = new Candy();
                        ingredients = new Recipe();
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
                    case "Water":
                        event = reader.nextEvent();
                        ingredients.water = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Sugar":
                        event = reader.nextEvent();
                        ingredients.sugar = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Fructose":
                        event = reader.nextEvent();
                        ingredients.fructose = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "Vanillin":
                        event = reader.nextEvent();
                        ingredients.vanillin = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "ChocolateType":
                        event = reader.nextEvent();
                        ingredients.chocolate = event.asCharacters().getData();
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
                if (endElement.getName().getLocalPart().equals("Candy")) {
                    candy.Ingredients = ingredients;
                    candy.Value = value;
                    candies.add(candy);
                }
            }
        }
        return candies;
    }
}