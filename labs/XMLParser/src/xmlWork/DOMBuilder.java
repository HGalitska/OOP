package xmlWork;

import candy.Candy;
import candy.Ingredient;
import candy.NutrValue;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Vector;

class DOMBuilder {
    static ArrayList<Candy> getCandyList(Element root) {
        ArrayList<Candy> candies = new ArrayList<>();
        NodeList candyNodes = children(root, "Candy");
        Candy candy;

        NodeList ingredientsNodes;

        for (int i = 0; i < candyNodes.getLength(); i++) {
            candy = new Candy();

            Element candyElem = (Element) candyNodes.item(i);

            candy.setID(new Integer(childValue(candyElem, "ID")));
            candy.setName(childValue(candyElem, "Name"));
            candy.setType(childValue(candyElem, "Type"));
            candy.setEnergy(new Integer(childValue(candyElem, "Energy")));

            Node recipeNode = child(candyElem, "ChocolateCandy");
            switch (candy.getType()) {
                case "Iris":
                    recipeNode = child(candyElem, "IrisCandy");
                    break;
                case "Caramel":
                    recipeNode = child(candyElem, "CaramelCandy");
                    break;
                case "ChocoFill":
                    recipeNode = child(candyElem, "ChocoFillCandy");
            }

            Vector<Ingredient> ingredients = new Vector<>();
            ingredientsNodes = recipeNode.getChildNodes();

            for (int ingr = 0; ingr < ingredientsNodes.getLength(); ingr++) {
                if (ingredientsNodes.item(ingr).getNodeType() == Node.ELEMENT_NODE) {
                    Element ingrElem = (Element) ingredientsNodes.item(ingr);
                    Ingredient currIngredient = new Ingredient();

                    currIngredient.setName(ingrElem.getTagName());
                    currIngredient.setQuantity(new Integer(ingrElem.getTextContent()));

                    currIngredient.setChocoType(ingrElem.getAttribute("chocoType"));
                    currIngredient.setFillType(ingrElem.getAttribute("fillType"));

                    ingredients.add(currIngredient);
                }
            }
            candy.setRecipe(ingredients);

            int prot = new Integer(childValue(candyElem, "Protein"));
            int fat = new Integer(childValue(candyElem, "Fat"));
            int carb = new Integer(childValue(candyElem, "Carbohydrate"));

            candy.setValue(new NutrValue(prot, fat, carb));
            candy.setProduction(childValue(candyElem, "Production"));

            candies.add(candy);
        }
        return candies;
    }

    private static Element child(Element parent, String childName) {
        NodeList list = parent.getElementsByTagName(childName);
        return (Element) list.item(0);
    }

    private static NodeList children(Element parent, String childName) {
        return parent.getElementsByTagName(childName);
    }

    private static String childValue(Element parent, String childName) {
        Element child = child(parent, childName);
        if (child == null) {
            return "-1";
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }
}