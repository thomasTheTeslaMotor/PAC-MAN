package classes;

import javax.swing.*;
import java.awt.*;

public class GamePitch extends JFrame {
    public static int frameWidth, frameHeight;
    PlayField playField;
    GamePitch() {
        // setting the frame up
        this.setTitle("Hungry-Ball"); // app title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (PlayField.selectedLevel.equals("Level3")){
            frameWidth = 736;
            frameHeight = 1020;
//            this.setSize(736, 1020);
        } else if (PlayField.selectedLevel.equals("Level4")) {
            frameWidth = 952;
            frameHeight = 840;
//            this.setSize(952, 840);
        }else {
            frameWidth = 736;
            frameHeight = 840;
//            this.setSize(736, 840);
        }// window dimensions
        this.setSize(frameWidth, frameHeight);

//        ImageIcon image = new ImageIcon("src/images/logo.jpg"); // app logo
        ImageIcon image = new ImageIcon("Task-5/src/images/logo.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.black); // window background color
        this.setLayout(new BorderLayout()); // layout of the frame

        // adding components to the frame
        KeyInput keyInput = new KeyInput();

        WallNorth north_wall = new WallNorth();
        WallSouth south_wall = new WallSouth();
        playField = new PlayField(keyInput, this);
        ScorePanel scorePanel = new ScorePanel(playField.player1, playField.player2);
        BottomPanel bottomPanel = new BottomPanel(playField.player1, playField.player2);
        // add the score on top of the north wall
        north_wall.setLayout(new BorderLayout());
        north_wall.add(scorePanel, BorderLayout.NORTH);

        // add the health and powerup panels beneath the south wall
        south_wall.setLayout(new BorderLayout());
        south_wall.add(bottomPanel, BorderLayout.SOUTH);

        this.addKeyListener(keyInput);
        this.getContentPane().add(north_wall, BorderLayout.NORTH); // set north wall
        this.getContentPane().add(south_wall, BorderLayout.SOUTH); // set south wall
        this.getContentPane().add(playField, BorderLayout.CENTER); // set play field

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
