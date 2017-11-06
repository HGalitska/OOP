import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ToHTML
{
    public static void transformToHTML() throws FileNotFoundException, TransformerException {
        TransformerFactory transFactory = TransformerFactory.newInstance();

        Source xslDoc = new StreamSource("xsl/candies.xsl");
        Source xmlDoc = new StreamSource("xml/candy.xml");

        String outputFileName="candies.html";

        OutputStream htmlFile = new FileOutputStream(outputFileName);
        Transformer transform = transFactory.newTransformer(xslDoc);
        transform.transform(xmlDoc, new StreamResult(htmlFile));

    }
}
