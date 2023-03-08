package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HealthPanel extends JPanel implements ActionListener{
    Player player;
    Timer timer;
    Image hearth;

    // this is in charge of letting the player select how many lives he wants to have!
    // keep in mind, the max is 9, the lowest 1
    public static int selectNumberOfLives = 3;

    public HealthPanel(Player player ){
        this.player = player;
        timer = new Timer(500, this);
        timer.start();
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(320, 50));
        this.setLayout(new FlowLayout());
        hearth = new ImageIcon(getClass().getClassLoader().getResource("hearth36px.png")).getImage();


    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        JLabel[] hearths = new JLabel[selectNumberOfLives];
        for(int i = 0; i < player.lives; i++){
            g2D.drawImage(hearth, 36 * (i) , 0, this);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
