package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {
    Player player1;
    Player player2;

    public BottomPanel(Player player1, Player player2) {
        if (PlayField.singlePlayer){
            this.player1 = player1;
            this.setBackground(Color.black);

            HealthPanel healthPanel1 = new HealthPanel(player1);

            this.setLayout(new BorderLayout());
            this.add(healthPanel1, BorderLayout.WEST);
        }
        if (!PlayField.singlePlayer){
            this.player1 = player1;
            this.player2 = player2;
            this.setBackground(Color.black);

            HealthPanel healthPanel1 = new HealthPanel(player1);
            HealthPanel healthPanel2 = new HealthPanel(player2);

            this.setLayout(new BorderLayout());
            this.add(healthPanel1, BorderLayout.EAST);
            this.add(healthPanel2, BorderLayout.WEST);
        }

    }


}
