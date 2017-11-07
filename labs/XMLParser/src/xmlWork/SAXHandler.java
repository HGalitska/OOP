package xmlWork;

import candy.NutrValue;
import candy.Recipe;
import candy.Candy;
import javafx.util.Pair;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class SAXHandler extends DefaultHandler {
    private List<Candy> candies; // container array, initialized in constructor

    // set of pairs for Candy object initialization
    private Pair<Boolean, Integer> ID = new Pair<>(false, 0);
    private Pair<Boolean, String> Name = new Pair<>(false, "None");
    private Pair<Boolean, String> Type = new Pair<>(false, "None");
    private Pair<Boolean, Integer> Energy = new Pair<>(false, 0);
    private Pair<Boolean, String> Prod = new Pair<>(false, "None");
    private Pair<Boolean, Integer> Water = new Pair<>(false, 0);
    private Pair<Boolean, Integer> Sugar = new Pair<>(false, 0);
    private Pair<Boolean, Integer> Fructose = new Pair<>(false, 0);
    private Pair<Boolean, Integer> Vanillin = new Pair<>(false, 0);
    private Pair<Boolean, String> Choc = new Pair<>(false, "None");
    private Pair<Boolean, Integer> Protein = new Pair<>(false, 0);
    private Pair<Boolean, Integer> Fat = new Pair<>(false, 0);
    private Pair<Boolean, Integer> Carbohydrate = new Pair<>(false, 0);

    SAXHandler(List<Candy> container){
        this.candies = container;
    }

    private void reset(){
        ID = new Pair<>(false, 0);
        Name = new Pair<>(false, "None");
        Type = new Pair<>(false, "None");
        Energy = new Pair<>(false, 0);
        Prod = new Pair<>(false, "None");
        Water = new Pair<>(false, 0);
        Sugar = new Pair<>(false, 0);
        Fructose = new Pair<>(false, 0);
        Vanillin = new Pair<>(false, 0);
        Choc = new Pair<>(false, "None");
        Protein = new Pair<>(false, 0);
        Fat = new Pair<>(false, 0);
        Carbohydrate = new Pair<>(false, 0);
    }

    //listener for start elements in xml file
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("ID")) {
            ID  = new Pair<>(true, ID.getValue());
        }
        else if (qName.equalsIgnoreCase("NAME")) {
            Name  = new Pair<>(true, Name.getValue());  // every time specific element
                                                        // is encountered, set its key to "true"
        }
        else if (qName.equalsIgnoreCase("TYPE")) {
            Type  = new Pair<>(true, Type.getValue());
        }
        else if (qName.equalsIgnoreCase("ENERGY")) {
            Energy  = new Pair<>(true, Energy.getValue());
        }
        else if (qName.equalsIgnoreCase("PRODUCTION")) {
            Prod  = new Pair<>(true, Prod.getValue());
        }
        else if (qName.equalsIgnoreCase("WATER")) {
            Prod  = new Pair<>(true, Prod.getValue());
        }
        else if (qName.equalsIgnoreCase("SUGAR")) {
            Sugar  = new Pair<>(true, Sugar.getValue());
        }
        else if (qName.equalsIgnoreCase("FRUCTOSE")) {
            Fructose  = new Pair<>(true, Fructose.getValue());
        }
        else if (qName.equalsIgnoreCase("VANILLIN")) {
            Vanillin  = new Pair<>(true, Vanillin.getValue());
        }
        else if (qName.equalsIgnoreCase("CHOCOLATETYPE")) {
            Choc  = new Pair<>(true, Choc.getValue());
        }
        else if (qName.equalsIgnoreCase("PROTEIN")) {
            Protein  = new Pair<>(true, Protein.getValue());
        }
        else if (qName.equalsIgnoreCase("FAT")) {
            Fat  = new Pair<>(true, Fat.getValue());
        }
        else if (qName.equalsIgnoreCase("CARBOHYDRATE")) {
            Carbohydrate  = new Pair<>(true, Carbohydrate.getValue());
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if(qName.equals("Candy")){          // if closing </Candy> element is encountered
            Recipe ingredients = new Recipe(Water.getValue(), Sugar.getValue(),
                                                        Fructose.getValue(), Vanillin.getValue(), Choc.getValue());
            NutrValue value = new NutrValue(Protein.getValue(), Fat.getValue(),
                                                Carbohydrate.getValue());

            // create a new candy with all encountered fields
            Candy myCandy = new Candy(ID.getValue(), Type.getValue(), Name.getValue(),
                                        Energy.getValue(), Prod.getValue(), ingredients, value);
            // add new candy to candy list
            this.candies.add(myCandy);

            reset(); //reset all candy properties to initial values
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if (ID.getKey()) {
            ID = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Name.getKey()) { //if certain element is encountered, save its value to later initialize Candy fields
            Name = new Pair<>(false, new String(ch, start, length));
        }
        if (Type.getKey()) {
            Type = new Pair<>(false, new String(ch, start, length));
        }
        if (Energy.getKey()) {
            Energy = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Prod.getKey()) {
            Prod = new Pair<>(false, new String(ch, start, length));
        }
        if (Sugar.getKey()) {
            Sugar = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Fructose.getKey()) {
            Fructose = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Water.getKey()) {
            Water = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Vanillin.getKey()) {
            Vanillin = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Choc.getKey()) {
            Choc = new Pair<>(false, new String(ch, start, length));
        }
        if (Protein.getKey()) {
            Protein = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Fat.getKey()) {
            Fat = new Pair<>(false, new Integer(new String(ch, start, length)));
        }
        if (Carbohydrate.getKey()) {
            Carbohydrate = new Pair<>(false, new Integer(new String(ch, start, length)));
        }

    }
}
