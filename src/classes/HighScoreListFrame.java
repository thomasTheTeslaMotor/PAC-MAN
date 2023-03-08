package classes.HighScoreList;

import classes.MainMenu;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreListFrame extends JFrame implements ActionListener {

    JButton back2Menu;
    MainMenu mainMenu;

    public HighScoreListFrame() {
        this.setBackground(Color.WHITE);
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);// window dimensions
        this.getContentPane().setBackground(Color.white);
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());

        String[] scoreList = buildList();
        for (int i = scoreList.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (getScore(scoreList[j]) < getScore(scoreList[j + 1])){
                    String temp = scoreList[j];
                    scoreList[j] = scoreList[j + 1];
                    scoreList[j + 1] = temp;

                }

            }
        }

        this.setLayout(new GridLayout(0, 1, 0, 20));

        JLabel message1 = new JLabel();
        message1.setText("Top Scorers");
        message1.setForeground(Color.red);
        message1.setFont(new Font("MV Boli", Font.PLAIN, 40));
        this.add(message1);


        for (String s: scoreList){
            JLabel message = new JLabel();
            message.setText(s + " pt");
            message.setForeground(Color.black);
            message.setFont(new Font("MV Boli", Font.PLAIN, 28));
            this.add(message);
        }

        back2Menu = new JButton("Back to Main-Menu");
        back2Menu.addActionListener(this);
        back2Menu.setFocusable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 10, 150, 50);
        buttonPanel.add(back2Menu);
        this.add(buttonPanel);



        this.setLocationRelativeTo(null);
        this.setVisible(true);

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

    private String[] buildList(){
        XmlParser xmlParser = new XmlParser();
        NodeList elementList = xmlParser.getElementList();
        String[] result = new String[elementList.getLength()];
        for (int i = 0; i < elementList.getLength(); i++) {
            Node element = elementList.item(i);

            if (element.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) element;
                result[i] = e.getAttribute("name");
            }
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back2Menu){
            mainMenu = new MainMenu();
            this.setVisible(false);
            mainMenu.setVisible(true);
            this.dispose();
        }
    }
}
