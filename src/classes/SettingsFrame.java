package classes;

import classes.HealthPanel;
import classes.LevelFrame;
import classes.MainMenu;
import classes.PlayField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame implements ActionListener {

    JPanel backPanel, categoriesPanel;
    JComboBox<String> livesBox, fpsBox, movementBox, paceBox;
    JLabel lives, fps, pace, movement;
    String[] livesList =  {"3", "1", "5", "9"};
    String[] fpsList =  {"60", "30", "100"};
    String[] paceList =  {"medium", "slow", "fast"};
    String[] movementList =  {"medium", "slow",  "fast"};
    JButton back;

    MainMenu mainMenu;

    public SettingsFrame(){
        this.setBackground(Color.WHITE);
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);// window dimensions
        this.getContentPane().setBackground(Color.black);

        // logo setup
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        this.setIconImage(logo.getImage());

        categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(0, 2, 0, 20));
        categoriesPanel.setSize(new Dimension(400, 300));
        categoriesPanel.setBounds(325, 480, 250, 150);



        //set panel for the back to MainMenu
        backPanel = new JPanel();
        backPanel.setBounds(20, 10, 50, 50);


        lives = new JLabel("Select number of Lives");
        lives.setForeground(Color.red);
        lives.setFont(new Font("MV Boli", Font.PLAIN, 24));

        fps = new JLabel("Select refreshing rate");
        fps.setForeground(Color.red);
        fps.setFont(new Font("MV Boli", Font.PLAIN, 24));

        movement = new JLabel("Select mouth animation \n speed");
        movement.setForeground(Color.red);
        movement.setFont(new Font("MV Boli", Font.PLAIN, 24));

        pace = new JLabel("Select game pace");
        pace.setForeground(Color.red);
        pace.setFont(new Font("MV Boli", Font.PLAIN, 24));

        // set Combobox for drop-down menu
        livesBox = new JComboBox<String>(livesList);
        livesBox.addActionListener(this);
        livesBox.setFocusable(false);

        movementBox = new JComboBox<String>(movementList);
        movementBox.addActionListener(this);
        movementBox.setFocusable(false);

        paceBox = new JComboBox<String>(paceList);
        paceBox.addActionListener(this);
        paceBox.setFocusable(false);

        fpsBox = new JComboBox<String>(fpsList);
        fpsBox.addActionListener(this);
        fps.setFocusable(false);

        // set back button
        back = new JButton("Back");
        back.addActionListener(this);
        back.setFocusable(false);


        categoriesPanel.add(lives);
        categoriesPanel.add(livesBox);

        categoriesPanel.add(fps);
        categoriesPanel.add(fpsBox);

        categoriesPanel.add(pace);
        categoriesPanel.add(paceBox);

        categoriesPanel.add(movement);
        categoriesPanel.add(movementBox);

        backPanel.add(back);

        JPanel fillIn1 = new JPanel();
        fillIn1.setBounds(20, 10, 50, 50);;
        JLabel title = new JLabel("Settings");
        title.setForeground(Color.red);
        title.setFont(new Font("MV Boli", Font.PLAIN, 40));
        fillIn1.add(title);
        JPanel fillIn2 = new JPanel();

        this.setLayout(new GridLayout(0, 1));
        this.add(fillIn1);
        this.add(categoriesPanel);
        this.add(fillIn2);
        this.add(backPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        // sets the wanted number of lives, the variable "HealthPanel.selectNumberOfLives" will be changed respectively. Note that the default is 3
        if (e.getSource() == livesBox){
            int l = Integer.parseInt(String.valueOf(livesBox.getSelectedItem()));
            HealthPanel.selectNumberOfLives = l;
            System.out.println("number of lives selected: "+l);
        }

        // goes back to MainMenu when button back is pressed
        if (e.getSource() == back){
            mainMenu = new MainMenu();
            this.setVisible(false);
            mainMenu.setVisible(true);
            this.dispose();
        }

        // sets fps value
        if (e.getSource() == fpsBox){
            String s = (String.valueOf(fpsBox.getSelectedItem()));
            if (s.equals("60")){
                PlayField.fps = 1.5;
            }else if (s.equals("30")){
                PlayField.fps = 3;
            }if (s.equals("100")){
                PlayField.fps = 1;
            }
            System.out.println("Selected FPS: " + s);
        }

        // sets pace value
        if (e.getSource() == paceBox){
            String s = (String.valueOf(paceBox.getSelectedItem()));
            if (s.equals("slow")){
                Player.speed = 2;
                Enemy.speed = 1;
            }else if (s.equals("medium")){
                Player.speed = 3;
                Enemy.speed = 2;
            }if (s.equals("fast")){
                Player.speed = 4;
                Enemy.speed = 3;
            }
            System.out.println("Selected pace: " + s);
        }

        // sets movement value
        if (e.getSource() == movementBox){
            String s = (String.valueOf(movementBox.getSelectedItem()));
            if (s.equals("slow")){
                Player.movement = 1;
            }else if (s.equals("medium")){
                Player.movement = 2;
            }if (s.equals("fast")){
                Player.movement = 3;
            }
            System.out.println("Selected movement behaviour: " + s);
        }

    }
}
