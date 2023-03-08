package classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    public String player1Key = "";
    public String player2Key = "";
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            player1Key = "right";
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            player1Key = "left";
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            player1Key = "down";
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            player1Key = "up";
        }


        if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("D key pressed");
            player2Key = "d";
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A key pressed");
            player2Key = "a";
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("S key pressed");
            player2Key = "s";
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("W key pressed");
            player2Key = "w";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
