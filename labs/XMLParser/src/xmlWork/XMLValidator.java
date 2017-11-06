package xmlWork;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;

public class XMLValidator {
    public boolean validateXML(String xsdFile, String xmlFile)
                                throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        factory.setSchema(schemaFactory.newSchema(new File(xsdFile))); // set my xsd schema as validating one

        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();

        reader.parse(new InputSource(xmlFile));
        return true;
    }
}