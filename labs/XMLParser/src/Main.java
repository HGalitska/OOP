import candy.Candy;
import com.thoughtworks.xstream.XStream;
import org.xml.sax.SAXException;
import xmlWork.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException {
        XMLValidator validator = new XMLValidator();
        boolean valid = validator.validateXML("xsd/candy.xsd", "xml/candy.xml");


        XMLParser parser = new XMLParser();
        File f = new File("xml/candy.xml");
        parser.parseXML(f, "STAX");
        ToHTML.transformToHTML();
    }
}
