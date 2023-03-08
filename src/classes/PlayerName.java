package classes;

import classes.EndGame.EndGame;
import classes.HighScoreList.HighScoreListFrame;
import org.w3c.dom.html.HTMLBRElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerName extends JFrame implements ActionListener {

    HighScoreListFrame highScoreListFrame;
    EndGame endGame;
    Player player;

    JButton set, cancel;
    JTextField textField1, textField2;
    JLabel names, p1, p2, space;
    GamePitch gamePitch;
    LevelFrame levelFrame;
    KeyInput keyInput;

    public PlayerName(){
        this.setTitle("Enter Name(s)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (PlayField.singlePlayer){
            this.setSize(320, 150);// window dimensions
        }else {
            this.setSize(320, 180);// window dimensions
        }
        this.getContentPane().setBackground(Color.black);

        // logo setup
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());

        //panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        if (PlayField.singlePlayer){
            names = new JLabel("Enter Name");
        }else {
            names = new JLabel("Enter Names");
        }
        space = new JLabel("");

        p1 = new JLabel("Player1:");
        textField1 = new JTextField();

        p2 = new JLabel("Player2:");
        textField2 = new JTextField();

        set = new JButton("Ok");
        set.addActionListener(this);
        set.setFocusable(false);

        cancel = new JButton("cancel");
        cancel.addActionListener(this);
        cancel.setFocusable(false);

//        panel.setLayout(new BorderLayout());
        if (PlayField.singlePlayer){
            panel.add(names);
            panel.add(space);
            panel.add(p1);
            panel.add(textField1);
            panel.add(cancel);
            panel.add(set);
        }else {
            panel.add(names);
            panel.add(space);
            panel.add(p1);
            panel.add(textField1);
            panel.add(p2);
            panel.add(textField2);
            panel.add(cancel);
            panel.add(set);
        }
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == set){
            gamePitch =  new GamePitch();

            if (PlayField.singlePlayer){
                System.out.println(textField1.getText());
                PlayField.playerName1 = textField1.getText();
                EndGame.name = textField1.getText();
            } else {
                PlayField.playerName1 = textField1.getText();
                PlayField.playerName2 = textField2.getText();
                EndGame.name = textField1.getText() + " and " + textField2.getText();;
            }


            this.setVisible(false);
            gamePitch.setLocationRelativeTo(null);
            gamePitch.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == cancel){
            levelFrame = new LevelFrame();
            this.setVisible(false);
            levelFrame.setLocationRelativeTo(null);
            levelFrame.setVisible(true);
            this.dispose();
        }

    }
}
