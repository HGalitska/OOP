package xmlWork;

import candy.Candy;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLParser {
    private List<Candy> candies = new ArrayList<>(); // list of candies from xml file

    public void parseXML(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser parser = factory.newSAXParser();
        DefaultHandler handler = new XMLHandler(candies); // setting my handler for xml elements
        parser.parse(xmlFile, handler);

        Comparator<Candy> comparator = new Sorter();
        Collections.sort(candies, comparator);

        for(Candy item : candies){
            item.show();
            System.out.println("\n");
        }
    }
}
