package classes;

import classes.HighScoreList.HighScoreListFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    GamePitch gamePitch;
    AboutFrame aboutFrame;
    LevelFrame levelFrame;
    SettingsFrame settingsFrame;
    JPanel buttonsPanel, aboutPanel, settingsPanel;
    JButton singlePlayerButton;
    JButton multiPlayerButton;
    JButton scoreboardButton;
    JButton aboutButton, settingsButton;

    public MainMenu(){
        // frame setup
        this.setTitle("Hungry-Ball"); // app title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);// window dimensions

        // logo setup
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());

        // background image setup
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("background.png"));

        // settings image setup
        ImageIcon settings = new ImageIcon(getClass().getClassLoader().getResource("settings.png"));

        // about image setup
        ImageIcon about = new ImageIcon(getClass().getClassLoader().getResource("about.png"));


        JLabel background_image = new JLabel(image);
        background_image.setBounds(0, 0, 900, 700);

        // set panel for single-, multiplayer and scoreboard buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.black);
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setBounds(325, 480, 250, 150);

        //set another panel for the about button
        aboutPanel = new JPanel();
        aboutPanel.setBackground(Color.black);
        aboutPanel.setLayout(new GridLayout(1, 1));
        aboutPanel.setBounds(70, 550, 50, 50);

        //set another panel for the settings button
        settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.black);
        settingsPanel.setLayout(new GridLayout(1, 1));
        settingsPanel.setBounds(780, 550, 50, 50);



        //main menu header setup
        JLabel header = new JLabel("Hungry-Ball");
        header.setBounds(250, 100, 900, 100);
        header.setForeground(Color.yellow);
        header.setFont(new Font("MV Boli", Font.PLAIN, 80));


        //buttons
        singlePlayerButton = new JButton("Select Single-Player");
        singlePlayerButton.setFont(new Font("MV Boli", Font.BOLD, 21));
        multiPlayerButton = new JButton("Select Multi-Player");
        multiPlayerButton.setFont(new Font("MV Boli", Font.BOLD, 21));
        scoreboardButton = new JButton("High-Score List");
        scoreboardButton.setFont(new Font("MV Boli", Font.BOLD, 21));
        aboutButton = new JButton(about);
        settingsButton = new JButton(settings);


        //button action setup
        singlePlayerButton.addActionListener(this);
        singlePlayerButton.setFocusable(false);

        multiPlayerButton.addActionListener(this);
        multiPlayerButton.setFocusable(false);

        scoreboardButton.addActionListener(this);
        scoreboardButton.setFocusable(false);

        buttonsPanel.add(singlePlayerButton);
        buttonsPanel.add(multiPlayerButton);
        buttonsPanel.add(scoreboardButton);

        // about button setup
        aboutButton.addActionListener(this);
        aboutButton.setFocusable(false);
        aboutPanel.add(aboutButton);

        // setting button setup
        settingsButton.addActionListener(this);
        settingsButton.setFocusable(false);
        settingsPanel.add(settingsButton);


        //adding elements to the frame
        this.add(header);
        this.add(aboutPanel);
        this.add(buttonsPanel);
        this.add(settingsPanel);
        this.add(background_image);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // open level window if singlePlayerButton is pressed
        if(e.getSource() == singlePlayerButton){
            levelFrame = new LevelFrame();
            this.setVisible(false);
            levelFrame.setLocationRelativeTo(null);
            levelFrame.setVisible(true);

            PlayField.singlePlayer = true;
            System.out.println(PlayField.singlePlayer);
        }

        // open level window if multiPlayerButton is pressed
        if(e.getSource() == multiPlayerButton){
            levelFrame = new LevelFrame();
            this.setVisible(false);
            levelFrame.setLocationRelativeTo(null);
            levelFrame.setVisible(true);

            PlayField.singlePlayer = false;
            System.out.println(PlayField.singlePlayer);
        }

        // open HighscoreList window if scoreboardButton is pressed
        if(e.getSource() == scoreboardButton){
            HighScoreListFrame highScoreListFrame = new HighScoreListFrame();
            this.setVisible(false);
            highScoreListFrame.setLocationRelativeTo(null);
            highScoreListFrame.setVisible(true);
        }

        //open about window if aboutButton is pressed
        if(e.getSource() == aboutButton){
            aboutFrame = new AboutFrame();
            this.setVisible(false);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setVisible(true);
        }

        //open setting window if settingsButton is pressed
        if(e.getSource() == settingsButton){
            settingsFrame = new SettingsFrame();
            this.setVisible(false);
            settingsFrame.setLocationRelativeTo(null);
            settingsFrame.setVisible(true);
        }
    }
}
