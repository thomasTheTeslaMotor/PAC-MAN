package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel implements ActionListener{
    Player player1;
    Player player2;
    Timer timer;
    JLabel score1;
    JLabel score2;
    JLabel total;
    int tot;

    ScorePanel(Player player1, Player player2){
        timer = new Timer(500, this);
        timer.start();

        if (PlayField.singlePlayer){
            this.player1 = player1;
            this.setBackground(Color.black);
            tot = player1.score;

            score1 = new JLabel(PlayField.playerName1 + ": " + player1.score);
            total = new JLabel("Total: "+ tot);
            score1.setForeground(Color.green);
            total.setForeground(Color.green);

            this.setLayout(new BorderLayout());
            this.add(score1, BorderLayout.EAST);
            this.add(total, BorderLayout.CENTER);

        }
        if (!PlayField.singlePlayer){
            this.player1 = player1;
            this.player2 = player2;
            this.setBackground(Color.black);
            tot = player1.score + player2.score;

            score1 = new JLabel(PlayField.playerName1 + ": " + player1.score);
            score2 = new JLabel(PlayField.playerName2 + ": " + player2.score);
            total = new JLabel("Total: "+ tot);
            score1.setForeground(Color.green);
            score2.setForeground(Color.green);
            total.setForeground(Color.green);

            this.setLayout(new BorderLayout());
            this.add(score1, BorderLayout.EAST);
            this.add(total, BorderLayout.CENTER);
            this.add(score2, BorderLayout.WEST);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        if(PlayField.singlePlayer){
            tot = player1.score;
            score1.setText(PlayField.playerName1 + "  : " + player1.score);
            total.setText("Total: " + tot);
        } else {
            tot = player1.score + player2.score;
            score1.setText(PlayField.playerName1 + " : " + player1.score);
            score2.setText(PlayField.playerName2 + " : " + player2.score);
            total.setText("                                                                                             Total: " + tot);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) { repaint();
    }
}
