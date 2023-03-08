package classes;

import javax.swing.*;
import java.awt.*;

public class PowerupPanel extends JPanel {
    PowerupPanel(){
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());

        JLabel powerup = new JLabel();
        ImageIcon fruit = new ImageIcon(getClass().getClassLoader().getResource("cherry36px.png"));

        fruit.setImage(fruit.getImage());

        powerup.setIcon(fruit);

        powerup.setForeground(Color.yellow);
        JPanel p1PowerUp = new JPanel();
        JPanel p2PowerUp = new JPanel();
        p1PowerUp.setBackground(Color.BLACK);
        p2PowerUp.setBackground(Color.BLACK);
        p1PowerUp.add(powerup);
        p2PowerUp.add(powerup);

        this.add(p1PowerUp, BorderLayout.WEST);
        this.add(p2PowerUp, BorderLayout.EAST);
    }
}
