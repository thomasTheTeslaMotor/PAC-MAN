package classes;

import classes.EndGame.EndGame;
import classes.HighScoreList.HighScoreListFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelFrame extends JFrame implements ActionListener {
    HighScoreListFrame highScoreListFrame;
    EndGame endGame;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton level4;
    JButton level5;
    JButton mMenu;
    JPanel panel;

    MainMenu mainMenu;

    KeyInput keyInput = new KeyInput();

    LevelFrame(){
        // frame setup
        this.setTitle("Hungry-Ball"); // app title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);// window dimensions
        this.getContentPane().setBackground(Color.black);

        // logo setup
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());


        //panel setup
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.setBackground(Color.black);
        //panel.setBounds(0, 0, 400, 500);



        //buttons setup
        level1 = new JButton("Level 1");
        level1.addActionListener(this);
        level1.setFocusable(false);

        level2 = new JButton("Level 2");
        level2.addActionListener(this);
        level2.setFocusable(false);

        level3 = new JButton("Level 3");
        level3.addActionListener(this);
        level3.setFocusable(false);

        level4 = new JButton("Level 4");
        level4.addActionListener(this);
        level4.setFocusable(false);

        level5 = new JButton("Level 5");
        level5.addActionListener(this);
        level5.setFocusable(false);

        mMenu = new JButton("Back to Main-Menu");
        mMenu.addActionListener(this);
        mMenu.setFocusable(false);

        panel.add(level1);
        panel.add(level2);
        panel.add(level3);
        panel.add(level4);
        panel.add(level5);
        panel.add(mMenu);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mMenu){
            mainMenu = new MainMenu();
            this.setVisible(false);
            mainMenu.setVisible(true);
        } else {

            if(e.getSource() == level1){
                PlayField.setLevel("Level1");
            }
            if(e.getSource() == level2){
                PlayField.setLevel("Level2");
            }
            if(e.getSource() == level3){
                PlayField.setLevel("Level3");
            }
            if(e.getSource() == level4){
                PlayField.setLevel("Level4");
            }
            if(e.getSource() == level5){
                PlayField.setLevel("Level5");
            }

            //checks if there isn't already a PlayerName, then prompts for PlayerName(s). Else starts the game directly.
            if (PlayField.playerName1 == null){
                PlayerName playerName = new PlayerName();
                this.setVisible(false);
                playerName.setLocationRelativeTo(null);
                playerName.setVisible(true);
            } else if (PlayField.playerName1 == null || PlayField.playerName2 == null) {
                PlayerName playerName = new PlayerName();
                this.setVisible(false);
                playerName.setLocationRelativeTo(null);
                playerName.setVisible(true);
            } else {
                GamePitch gamePitch = new GamePitch();
                this.setVisible(false);
                gamePitch.setLocationRelativeTo(null);
                gamePitch.setVisible(true);
            }


        }


    }
}
