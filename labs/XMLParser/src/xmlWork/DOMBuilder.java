package xmlWork;

import candy.Candy;
import candy.NutrValue;
import candy.Recipe;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

class DOMBuilder {
    static ArrayList<Candy> getCandyList(Element root) {
        ArrayList<Candy> candies = new ArrayList<>();
        NodeList candyNodes = children(root, "Candy");
        Candy candy;

        for (int i = 0; i < candyNodes.getLength(); i++) {
            candy = new Candy();

            Element candyElem = (Element) candyNodes.item(i);

            candy.ID = new Integer(childValue(candyElem, "ID"));
            candy.Name = childValue(candyElem, "Name");
            candy.Type = childValue(candyElem, "Type");
            candy.Energy = new Integer(childValue(candyElem, "Energy"));

            int water = new Integer(childValue(candyElem, "Water"));
            int sugar = new Integer(childValue(candyElem, "Sugar"));
            int fruct = new Integer(childValue(candyElem, "Fructose"));
            int vanil = new Integer(childValue(candyElem, "Vanillin"));
            String choco = childValue(candyElem, "ChocolateType");
            candy.Ingredients = new Recipe(water, sugar, fruct, vanil, choco);

            int prot = new Integer(childValue(candyElem, "Protein"));
            int fat = new Integer(childValue(candyElem, "Fat"));
            int carb = new Integer(childValue(candyElem, "Carbohydrate"));
            candy.Value = new NutrValue(prot, fat, carb);
            candy.Production = childValue(candyElem, "Production");

            candies.add(candy);
        }
        return candies;
    }

    private static Element child(Element parent, String childName) {
        NodeList list = parent.getElementsByTagName(childName);
        return  (Element)list.item(0);
    }

    private static NodeList children(Element parent, String childName) {
        return parent.getElementsByTagName(childName);
    }

    private static String childValue(Element parent, String childName) {
        Element child = child(parent, childName);
        if (child == null){
            return "-1";
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }
}