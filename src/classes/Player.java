package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Player {
    private int yVelocity;
    private int xVelocity;
    private int x;
    private int y;
    private int[][] level;
    private String[] controls;
    private Image pacman, pacman_down, pacman_left, pacman_up, pacman_right;
    int height;
    int width;
    public static int speed = 3;
    public static int movement = 2;
    int counter = 0;
    int counter2 = 0; //***
    boolean undefeatable = false;
    int lives = HealthPanel.selectNumberOfLives;
    int lostLife = 0; //***
    int score = 0;
    ArrayList<Enemy> killedEnemies = new ArrayList<>();

    public Player(int[][] level){
        if (movement == 1) {
            // pac-man-right setup
            pacman_right = new ImageIcon(getClass().getClassLoader().getResource("low-right.gif")).getImage();
            // pac-man facing down
            pacman_down = new ImageIcon(getClass().getClassLoader().getResource("low-down.gif")).getImage();
            // pac-man facing left
            pacman_left = new ImageIcon(getClass().getClassLoader().getResource("low-left.gif")).getImage();
            // pac-man facing up
            pacman_up = new ImageIcon(getClass().getClassLoader().getResource("low-up.gif")).getImage();
            pacman = pacman_right;
            this.level = level;
        }else if (movement == 2) {
            // pac-man-right setup
            pacman_right = new ImageIcon(getClass().getClassLoader().getResource("medium-right.gif")).getImage();
            // pac-man facing down
            pacman_down = new ImageIcon(getClass().getClassLoader().getResource("medium-down.gif")).getImage();
            // pac-man facing left
            pacman_left = new ImageIcon(getClass().getClassLoader().getResource("medium-left.gif")).getImage();
            // pac-man facing up
            pacman_up = new ImageIcon(getClass().getClassLoader().getResource("medium-up.gif")).getImage();
            pacman = pacman_right;
            this.level = level;
        }else if (movement == 3) {
            // pac-man-right setup
            pacman_right = new ImageIcon(getClass().getClassLoader().getResource("fast-right.gif")).getImage();
            // pac-man facing down
            pacman_down = new ImageIcon(getClass().getClassLoader().getResource("fast-down.gif")).getImage();
            // pac-man facing left
            pacman_left = new ImageIcon(getClass().getClassLoader().getResource("fast-left.gif")).getImage();
            // pac-man facing up
            pacman_up = new ImageIcon(getClass().getClassLoader().getResource("fast-up.gif")).getImage();
            pacman = pacman_right;
            this.level = level;
        }
    }

    public void setXY(int NewX, int NewY){
        this.x = NewX;
        this.y = NewY;
    }

    public void setControls(String[] controls){
        this.controls = controls;
    }

    public void setMargins(int newWidth, int newHeight){
        this.width = newWidth;
        this.height = newHeight;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getPacman(){
        return pacman;
    }

    private void turn(String direction){
        if (direction.equals(controls[1])){
            xVelocity = speed;
        }
        else if (direction.equals(controls[0])){
            xVelocity = -1 * speed;
        }else {
            xVelocity = 0;
        }

        if (direction.equals(controls[2])){
            yVelocity = -1 * speed;
        }
        else if (direction.equals(controls[3])){
            yVelocity = speed;
        }
        else {
            yVelocity = 0;
        }
    }

    public void decreaseLives(){
        if (lives >= 1){
            lives --;
            lostLife ++;
            System.out.println(lives);
        }
    }

    private void clearPosition(int xPosition, int yPosition){
        try{
            if ((level[yPosition][xPosition] == 0) && y % 36 == 0 && x % 36 == 0){
                score += 50;
                level[yPosition][xPosition] = 2;
            }
            if ((level[yPosition][xPosition] == 7) && y % 36 == 0 && x % 36 == 0){
               // speed = 3;
                counter = 0;
                undefeatable = true;
                killedEnemies = new ArrayList<>();
                level[yPosition][xPosition] = 3;
            }
        }catch(Exception exception){

        }

    }
    public void stop(){
        xVelocity = 0;
        yVelocity = 0;
        pacman = pacman_right;
    }

    public void move(String currentKey){

        int xPosition = x / 36;
        int yPosition = y / 36;
        counter ++;
        counter2 ++;
        /*
        If player is undefeatable for 500 steps, the undefeatable effect disappears. Counter counts the number of steps
        ran with the undefeatable effect.
         */
        if (counter >= 500){
            undefeatable = false;
            counter = 0;
        }

        /*
        If a player survives a time step in the game he is rewarded with 5 points
        */
        if (counter2 >= 10){
            this.score += 5;
            counter2 = 0;
        }

        if (lostLife == 1){
            counter2 = 0;
            lostLife = 0;
        }

        /*
        Change direction if there is no wall to stop the player.
         */

        try {
            if (currentKey.equals(controls[0])) {

                if (level[yPosition][xPosition - 1] != 1 && x % 36 == 0 && y % 36 == 0) {
                    turn(currentKey);
                }
            }

            if (currentKey.equals(controls[1])) {
                if (level[yPosition][xPosition + 1] != 1 && x % 36 == 0 && y % 36 == 0) {
                    turn(currentKey);
                }
            }

            if (currentKey.equals(controls[2])) {
                if (level[yPosition - 1][xPosition] != 1 && y % 36 == 0 && x % 36 == 0) {
                    turn(currentKey);

                }
            }

            if (currentKey.equals(controls[3])) {
                if (level[yPosition + 1][xPosition] != 1 && y % 36 == 0 && x % 36 == 0) {
                    turn(currentKey);
                }
            }
        }catch(Exception exception){

        }



        /*
        Stopping player if it hits a wall
         */

        try {
            if (level[yPosition][xPosition - 1] == 1 && x % 36 == 0 && y % 36 == 0 && xVelocity == -1 * speed) {
                xVelocity = 0;
                yVelocity = 0;
            } else if (level[yPosition][xPosition + 1] == 1 && x % 36 == 0 && y % 36 == 0 && xVelocity == speed) {
                xVelocity = 0;
                yVelocity = 0;
            } else if (level[yPosition - 1][xPosition] == 1 && x % 36 == 0 && y % 36 == 0 && yVelocity == -1 * speed) {
                xVelocity = 0;
                yVelocity = 0;
            } else if (level[yPosition + 1][xPosition] == 1 && x % 36 == 0 && y % 36 == 0 && yVelocity == speed) {
                xVelocity = 0;
                yVelocity = 0;
            }
        }catch(Exception exception){

        }
        /*
        Making the eatable or power up disappear.
         */
        if (x % 36 == 0 && y % 36 == 0 ) {
            clearPosition(xPosition, yPosition);
        }


         /*
        Updating x and y in regard to the new velocities
         */
        x += xVelocity;
        y += yVelocity;

        /*
        Adjusting players mouth direction
         */

        if (xVelocity == speed) {
            pacman = pacman_right;
        }
        if (xVelocity == -1 * speed) {
            pacman = pacman_left;
        }
        if (yVelocity == speed) {
            pacman = pacman_down;
        }
        if (yVelocity == -1 * speed) {
            pacman = pacman_up;
        }

        /*
        Redirecting player if it goes over the margin.
         */
        if (x >= width) {
            x = 0;
            level[y/36][x/36] = 2;
        }
        if (x < 0) {
            x = width;
        }
        if (y >= height) {
            y = 0;
            level[y/36][x/36] = 2;
        }
        if (y < 0) {
            y = height;
        }




    }



}
