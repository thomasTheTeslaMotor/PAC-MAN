package classes.EndGame;

import classes.HighScoreList.HighScoreListFrame;
import classes.HighScoreList.XmlParser;
import classes.MainMenu;
import classes.PlayField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EndGame extends JFrame implements ActionListener {
    public static String name;
    private JButton back2Menu, hichScore, insertInList;
    MainMenu mainMenu;
    int score;

    public EndGame(int score) {
        this.setBackground(Color.WHITE);
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);// window dimensions
        this.getContentPane().setBackground(Color.white);
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());

        this.score = score;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 1, 0, 5));


        JPanel hichScorePanel = new JPanel();
        hichScorePanel.setBounds(20, 10, 200, 75);
        hichScore = new JButton("Top Scorers List");
        hichScore.addActionListener(this);
        hichScorePanel.add(hichScore);
        buttonsPanel.add(hichScorePanel);

        JPanel insertInListPanel = new JPanel();
        insertInListPanel.setBounds(20, 10, 200, 50);
        insertInList = new JButton("Add your name to Top Scorers ");
        insertInList.addActionListener(this);
        insertInListPanel.add(insertInList);
        buttonsPanel.add(insertInListPanel);

        JPanel back2MenuPanel = new JPanel();
        back2MenuPanel.setBounds(20, 10, 200, 50);
        back2Menu = new JButton("Back to Main Menu");
        back2Menu.addActionListener(this);
        back2MenuPanel.add(back2Menu);
        buttonsPanel.add(back2MenuPanel);


        JPanel fillIn1 = new JPanel();
        fillIn1.setBounds(20, 10, 50, 50);;
        JLabel title = new JLabel("Game Over");
        title.setForeground(Color.red);
        title.setFont(new Font("MV Boli", Font.PLAIN, 40));
        fillIn1.add(title);
        JPanel fillIn2 = new JPanel();

        this.setLayout(new GridLayout(0, 1));
        this.add(fillIn1);
        this.add(buttonsPanel);
        this.add(fillIn2);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back2Menu){
            mainMenu = new MainMenu();
            this.setVisible(false);
            mainMenu.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == hichScore){
            HighScoreListFrame highScoreListFrame = new HighScoreListFrame();
            this.setVisible(false);
            highScoreListFrame.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == insertInList){
            String username = name;
            System.out.println(username);
            XmlParser xmlParser = new XmlParser();
            NodeList elementList = xmlParser.getElementList();

            Node element = elementList.item(0);
            Node parent = element.getParentNode();
            Document document= xmlParser.getDocument();
            Element newElement = document.createElement("item");
            newElement.setAttribute("name", username + " " + Integer.toString(score));

            if (elementList.getLength() >= 10){
                Element minNode = (Element) getMinScore(elementList);
                if (getScore(minNode.getAttribute("name")) < getScore(newElement.getAttribute("name"))){
                    parent.removeChild(getMinScore(elementList));
                    parent.appendChild(newElement);
                }
            }else{
                parent.appendChild(newElement);
            }


            //Save changes into XML file. Some parts are copied from www.w3schools.com
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer=
                    null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException transformerConfigurationException) {
                transformerConfigurationException.printStackTrace();
            }
            DOMSource source = new DOMSource(document);
            StreamResult result=new StreamResult(new File("src/xml/high_score_list.xml"));
            try {
                transformer.transform(source, result);
                //For console Output.
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            } catch (TransformerException transformerException) {
                transformerException.printStackTrace();
            }

        }

    }

    private Node getMinScore(NodeList elementList){
        Node min = elementList.item(0);

        for (int i = 0; i < elementList.getLength(); i++){
            Node element = elementList.item(i);
            if (getScore(((Element)min).getAttribute("name")) > getScore(((Element)element).getAttribute("name"))){
                min = element;
            }
        }

        return min;
    }

    private int getScore(String s){
        int result;
        int i = s.length() - 1;
        String number = "";
        while (Character.isDigit(s.charAt(i))){
            number = Character.toString(s.charAt(i)) + number;
            i -= 1;
        }
        result = Integer.parseInt(number);
        return result;
    }
}
