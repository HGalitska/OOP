package xmlWork;

import candy.Candy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLParser {
    private List<Candy> candies = new ArrayList<>(); // list of candies from xml file

    public void parseXML(File xmlFile, String parserName) throws SAXException, IOException, ParserConfigurationException, XMLStreamException {
        if(parserName.equals("SAX")) {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new SAXHandler(candies); // setting my handler for xml elements
            parser.parse(xmlFile, handler);
        }
        else if(parserName.equals("DOM")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/candy.xml");

            Element root = doc.getDocumentElement();
            candies = DOMBuilder.getCandyList(root);
        }
        else if(parserName.equals("STAX")) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new StreamSource("xml/candy.xml"));
            STAXHandler handler = new STAXHandler(reader);
            candies = handler.getCandyList();
        }

        Comparator<Candy> comparator = new Sorter();
        Collections.sort(candies, comparator);

        for(Candy item : candies){
            item.show();
            System.out.println("\n");
        }
    }
}
