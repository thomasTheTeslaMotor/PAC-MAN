package classes;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Level {

    static int[][] level;

    public static int[][] level1 = new int[20][20];
    public static int[][] level2 = new int[20][20];
    public static int[][] level3 = new int[25][20];
    public static int[][] level4 = new int[20][26];
    public static int[][] level5 = new int[20][20];


    public int[][] getLevelData(String lev){
        System.out.println("getLevelData"+ lev);

        File file1 = new File("src/xml/Level1.xml");
        File file2 = new File("src/xml/Level2.xml");
        File file3 = new File("src/xml/Level3.xml");
        File file4 = new File("src/xml/Level4.xml");
        File file5 = new File("src/xml/Level5.xml");


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        if (lev.equals("Level1")){
            Document doc;
            try {
                doc = documentBuilder.parse(file1);
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("array");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String MessageText = element.getElementsByTagName("column").item(0).getTextContent();
                    for (int j = 0; j <20; j++){
                        int jo = Integer.valueOf(MessageText.charAt(j)) - 48;
//                        System.out.println(jo);
                        level1[i][j] = jo;
                    }
                }
            }
            level = level1;
        } else if (lev.equals("Level2")) {
            Document doc;
            try {
                doc = documentBuilder.parse(file2);
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("array");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String MessageText = element.getElementsByTagName("column").item(0).getTextContent();
                    for (int j = 0; j <20; j++){
                        int jo = Integer.valueOf(MessageText.charAt(j)) - 48;
                        level2[i][j] = jo;
                    }
                }
            }
            level = level2;
        } else if (lev.equals("Level3")) {
            Document doc;
            try {
                doc = documentBuilder.parse(file3);
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("array");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String MessageText = element.getElementsByTagName("column").item(0).getTextContent();
                    for (int j = 0; j <20; j++){
                        int jo = Integer.valueOf(MessageText.charAt(j)) - 48;
                        level3[i][j] = jo;
                    }
                }
            }
            level = level3;
        } else if (lev.equals("Level4")) {
            Document doc;
            try {
                doc = documentBuilder.parse(file4);
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("array");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String MessageText = element.getElementsByTagName("column").item(0).getTextContent();
                    for (int j = 0; j <26; j++){
                        int jo = Integer.valueOf(MessageText.charAt(j)) - 48;
                        level4[i][j] = jo;
                    }
                }
            }
            level = level4;
        } else if (lev.equals("Level5")) {
            Document doc;
            try {
                doc = documentBuilder.parse(file5);
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("array");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String MessageText = element.getElementsByTagName("column").item(0).getTextContent();
                    for (int j = 0; j <20; j++){
                        int jo = Integer.valueOf(MessageText.charAt(j)) - 48;
                        level5[i][j] = jo;
                    }
                }
            }
            level = level5;
        }
        return level;
    }

}
