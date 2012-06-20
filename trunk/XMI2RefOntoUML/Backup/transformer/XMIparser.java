package transformer;
 
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
 
import java.lang.String;
 
public class XMIparser {
 
    final static String ATTRIBUTES = "UML:Attribute";
    final static String METHODS = "UML:Operation";
    final static String CLASSES = "UML:Class";
    final static String COUPLINGS = "UML:AssociationEnd";
    final static String STEREOTYPE = "UML:ModelElement.stereotype";
    final static String CLASS_INHERITANCES = "UML:GeneralizableElement.generalization";
    final static String FILE_ADDRESS = "sample.xmi";
 
    public static void main(String args[]) {
        try {
 
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(FILE_ADDRESS));
 
            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("==============================");
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
 
            NodeList listClass = doc.getElementsByTagName(CLASSES);
            int totalClass = listClass.getLength();
            System.out.println("Total Class : " + totalClass);
 
            NodeList listAttributes = doc.getElementsByTagName(ATTRIBUTES);
            int totalAttributes = listAttributes.getLength();
            System.out.println("Total Attribute : " + totalAttributes);
 
            NodeList linksAtributesHiding = doc.getElementsByTagName(ATTRIBUTES);
            for (int i = 0; i < linksAtributesHiding.getLength(); i++) {
 
                Element link = (Element) linksAtributesHiding.item(i);
 
                System.out.println("attribute value = " + link.getAttribute("visibility"));
 
            }
 
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
 
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
 
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}