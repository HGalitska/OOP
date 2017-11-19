package xmlWork;

import candy.Candy;
import candy.NutrValue;
import candy.recipes.*;
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

            candy.ID = new Integer(childValue(candyElem, "ID"));
            candy.Name = childValue(candyElem, "Name");
            candy.Type = childValue(candyElem, "Type");
            candy.Energy = new Integer(childValue(candyElem, "Energy"));

            Node recipeNode = child(candyElem, "ChocolateCandy");
            Recipe recipe = new ChocoRecipe();

            switch (candy.Type) {
                case "Iris":
                    recipeNode = child(candyElem, "IrisCandy");
                    recipe = new IrisRecipe();
                    break;
                case "Caramel":
                    recipeNode = child(candyElem, "CaramelCandy");
                    recipe = new CaramelRecipe();
                    break;
                case "ChocoFill":
                    recipeNode = child(candyElem, "ChocoFillCandy");
                    recipe = new ChocoFillRecipe();
            }

            Vector<Ingredient> ingredients = new Vector<>();
            ingredientsNodes = recipeNode.getChildNodes();

            for (int ingr = 0; ingr < ingredientsNodes.getLength(); ingr++) {
                if (ingredientsNodes.item(ingr).getNodeType() == Node.ELEMENT_NODE) {
                    Element ingrElem = (Element) ingredientsNodes.item(ingr);
                    Ingredient currIngredient = new Ingredient();

                    currIngredient.name = ingrElem.getTagName();
                    currIngredient.quantity = new Integer(ingrElem.getTextContent());

                    currIngredient.chocoType = ingrElem.getAttribute("chocoType");
                    currIngredient.fillType = ingrElem.getAttribute("fillType");

                    ingredients.add(currIngredient);
                }
            }

            recipe.updateIngredients(ingredients);
            candy.recipe = recipe;

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