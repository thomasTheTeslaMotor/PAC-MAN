package classes.HighScoreList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {
    public NodeList elementList;
    public Document document;
    public XmlParser(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File("src/xml/high_score_list.xml"));
            document.getDocumentElement().normalize();
            elementList = document.getElementsByTagName("item");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    public NodeList getElementList(){
        return elementList;
    }

    public Document getDocument() {
        return document;
    }
}
