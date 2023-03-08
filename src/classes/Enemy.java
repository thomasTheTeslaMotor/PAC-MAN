package classes;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Enemy {
    private int[][] level;
    private int xVelocity;
    private int yVelocity;
    private int x;
    private int y;
    private int xOrigin;
    private int yOrigin;
    public static  int speed = 2;
    boolean defeated = false;
    int counter = 0;
    Image enemyImage, enemyScared, enemyHappy;

    public Enemy(int newX, int newY, int[][] newLevel){
        this.x = newX;
        this.y = newY;
        this.level = newLevel;
        xOrigin = newX;
        yOrigin = newY;
        enemyHappy = new ImageIcon(getClass().getClassLoader().getResource("phantom-blue36px.png")).getImage();
        enemyScared = new ImageIcon(getClass().getClassLoader().getResource("ghost-scared.png")).getImage();
        enemyImage = enemyHappy;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void resetCoordinates(){
        if (PlayField.selectedLevel.equals("Level1")){
            x = 10*36;
            y = 11*36;
        }
        if (PlayField.selectedLevel.equals("Level2")){
            x = 10*36;
            y = 9*36;
        }
        if (PlayField.selectedLevel.equals("Level3")){
            x = 10*36;
            y = 15*36;
        }
        if (PlayField.selectedLevel.equals("Level4")){
            x = 15*36;
            y = 11*36;
        }
        if (PlayField.selectedLevel.equals("Level5")){
            x = 10*36;
            y = 8*36;
        }
    }
    private String randomDirection(ArrayList<String> directions){
        int index = (int)(Math.random() * directions.size());
        return directions.get(index);
    }

    public void defeat(){
        defeated = true;
        counter = 0;
        xVelocity = 0;
        yVelocity = 0;
    }

    private ArrayList<String> checkAvailableDirections(){
        ArrayList<String> result = new ArrayList();
        int xPosition = x / 36;
        int yPosition = y / 36;
        if (level[yPosition][xPosition - 1] != 1 && x % 36 == 0 && y % 36 == 0) {
            result.add("left");
        }
        if (level[yPosition][xPosition + 1] != 1 && x % 36 == 0 && y % 36 == 0) {
            result.add("right");
        }
        if (level[yPosition - 1][xPosition] != 1 && y % 36 == 0 && x % 36 == 0) {
            result.add("up");
        }
        if (level[yPosition + 1][xPosition] != 1 && y % 36 == 0 && x % 36 == 0) {
            result.add("down");
        }


        return result;
    }
    private void adjustVelocitys(String direction){
        if (direction.equals("right")){
            xVelocity =  speed;
        }else if (direction.equals("left")){
            xVelocity =  -1 * speed;
        }else {
            xVelocity = 0;
        }
        if (direction.equals("up")){
            yVelocity = -1 * speed;
        }else if (direction.equals("down")){
            yVelocity = speed;
        }else {
            yVelocity = 0;
        }

    }

    private void pickDirection(String s){
        ArrayList<String> directions = checkAvailableDirections();
        if (directions.size() >= 2){
            directions.remove(s);
            String direction = randomDirection(directions);
            adjustVelocitys(direction);

        }else{
            adjustVelocitys(directions.get(0));
        }
    }

    public void move() {
        /*
        Keeping the defeated effect for 50 time steps. Counter counts the time steps.
         */
//        counter ++;
        if (defeated) { counter ++; }

        if (counter == 50){
            defeated = false;
            counter = 0;
        }
        if (xVelocity == 0 && yVelocity == 0) {
            String direction = randomDirection(checkAvailableDirections());
            adjustVelocitys(direction);
        }

         /*
         Redirecting player if he goes over the margin
          */
        if (x >= GamePitch.frameWidth-16) {
            x = 0;
        }
        if (x < 0) {
            x = GamePitch.frameWidth-16;
        }
        if (y >= GamePitch.frameHeight-120) {
            y = 0;
        }
        if (y < 0) {
            y = GamePitch.frameHeight-120;
        }

        /*
        Randomly redirecting enemy when it hits a wall or a new direction is available
         */

        try {

            if (x % 36 == 0 && y % 36 == 0 && xVelocity == -1 * speed) {
                pickDirection("right");
            }else if (x % 36 == 0 && y % 36 == 0 && xVelocity == speed) {
                pickDirection("left");
            }else if (x % 36 == 0 && y % 36 == 0 && yVelocity == -1 * speed) {
                pickDirection("down");
            }else if (x % 36 == 0 && y % 36 == 0 && yVelocity == 1 * speed) {
                pickDirection("up");
            }
        }catch (Exception e ){

        }

        /*
        Updating the x and y coordinates
         */
        x += xVelocity;
        y += yVelocity;
    }
}
