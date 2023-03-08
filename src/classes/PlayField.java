package classes;

import classes.EndGame.EndGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayField  extends JPanel implements ActionListener {
    Level levelObj = new Level();
    Player player1;
    Player player2;
    Image eatable;
    Image powerup;
    GamePitch gamePitch;
    Image wall;
    Timer timer;
    KeyInput keyInput;
    Enemy[] phantoms;
    public static String selectedLevel;
    public static Boolean singlePlayer;
    public static double fps = 1; // setting for 30 fps standard.
    private double fpsCounter = 0;

    boolean gameOver = false;

    public static String playerName1, playerName2;

    // the actual number of eatable objects already eaten
    public static int eatenPoints;




    // in the matrix 1 = wall, 0 = empty/eatable object, 7 = powerup
    //int[][] level1 = Level.getLevelData(getLevel());

    int[][] level1 = levelObj.getLevelData(getLevel());

    public  static final String blue = "\033[1;34m";
    public  static final String reset = "\033[0m";


    public static void setLevel(String le){
        System.out.println("setLevel to: " + le);
        selectedLevel = le;
    }
    public String getLevel(){
        return selectedLevel;
    }

    private boolean hasWon(){
        for (int i = 0; i < level1.length; i ++){
            for (int j = 0; j < level1[0].length; j ++){
                if (level1[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }



    PlayField(KeyInput keyInput, GamePitch gamePitch){
        this.gamePitch = gamePitch;
        initiatePlayers();
        initiatePhantoms();
        this.keyInput = keyInput;
        this.setBackground(Color.black);
        timer = new Timer(10, this);
        timer.start();

        /*
        eatable setup
         */
        eatable = new ImageIcon(getClass().getClassLoader().getResource("point9px.png")).getImage();

        /*
        powerup setup
         */
        powerup = new ImageIcon(getClass().getClassLoader().getResource("cherry18px.png")).getImage();

        /*
        wall setup
         */
        wall = new ImageIcon(getClass().getClassLoader().getResource("wall35px.jpg")).getImage();

    }

    public int getScore(){
        if (singlePlayer){
            return player1.score;
        } else {
            return player1.score + player2.score;
        }
    }

    private void initiatePlayers(){
        System.out.println("window width: "+ this.getWidth());

        System.out.println("window height: "+ this.getHeight());
        if (singlePlayer) {
            String[] controls1 = new String[4];
            controls1[0] = "left";
            controls1[1] = "right";
            controls1[2] = "up";
            controls1[3] = "down";

            player1 = new Player(level1);
            player1.setControls(controls1);
//            if (selectedLevel.equals("Level3")){
//                player1.setMargins(736, 900);
//            }
//            if (selectedLevel.equals("Level4")){
//                player1.setMargins(952, 736);
//            } else {
//                player1.setMargins(736, 736);
//            }

            player1.setMargins(GamePitch.frameWidth-16, GamePitch.frameHeight-120);
//        player1.setXY(36 * 5, 13 * 36);


            if (selectedLevel.equals("Level1")){
                player1.setXY(36 * 5, 13 * 36);
            } else if (selectedLevel.equals("Level2")) {
                player1.setXY(36 * 5, 9 * 36);
            } else if (selectedLevel.equals("Level3")) {
                player1.setXY(36 * 5, 19 * 36);
            } else if (selectedLevel.equals("Level4")) {
                player1.setXY(36 * 5, 11 * 36);
            } else if (selectedLevel.equals("Level5")) {
                player1.setXY(36 * 6, 12 * 36);
            }
        } else {
            String[] controls1 = new String[4];
            controls1[0] = "left";
            controls1[1] = "right";
            controls1[2] = "up";
            controls1[3] = "down";

            player1 = new Player(level1);
            player1.setControls(controls1);
            player1.setMargins(GamePitch.frameWidth-16, GamePitch.frameHeight-120);
//        player1.setXY(36 * 5, 13 * 36);

            String[] controls2 = new String[4];
            controls2[0] = "a";
            controls2[1] = "d";
            controls2[2] = "w";
            controls2[3] = "s";

            player2 = new Player(level1);
            player2.setControls(controls2);
            player2.setMargins(GamePitch.frameWidth-16, GamePitch.frameHeight-120);
//        player2.setXY(7 * 36, 13 * 36);


            if (selectedLevel.equals("Level1")){
                player1.setXY(36 * 7, 13 * 36);
                player2.setXY(5 * 36, 13 * 36);
            } else if (selectedLevel.equals("Level2")) {
                player1.setXY(36 * 7, 9 * 36);
                player2.setXY(5 * 36, 9 * 36);
            } else if (selectedLevel.equals("Level3")) {
                player1.setXY(36 * 7, 19 * 36);
                player2.setXY(5 * 36, 19 * 36);
            } else if (selectedLevel.equals("Level4")) {
                player1.setXY(36 * 7, 11 * 36);
                player2.setXY(5 * 36, 11 * 36);
            } else if (selectedLevel.equals("Level5")) {
                player1.setXY(36 * 8, 12 * 36);
                player2.setXY(6 * 36, 12 * 36);
            }
        }
    }

    private void initiatePhantoms(){
        if (selectedLevel.equals("Level1")){
            phantoms = new Enemy[5];
            phantoms[0] = new Enemy(3 * 36, 36, level1);
            phantoms[1] = new Enemy(9 * 36, 4 * 36, level1);
            phantoms[2] = new Enemy(18 * 36, 12 * 36, level1);
            phantoms[3] = new Enemy( 3 * 36, 14 * 36, level1);
            phantoms[4] = new Enemy(18 * 36, 10 * 36, level1);
        } else if (selectedLevel.equals("Level2")) {
            phantoms = new Enemy[6];
            phantoms[0] = new Enemy(2 * 36, 36, level1);
            phantoms[1] = new Enemy(13 * 36, 4 * 36, level1);
            phantoms[2] = new Enemy(18 * 36, 4 * 36, level1);
            phantoms[3] = new Enemy( 13* 36, 11* 36, level1);
            phantoms[4] = new Enemy(18 * 36, 15* 36, level1);
            phantoms[5] = new Enemy(8 * 36, 18* 36, level1);
        } else if (selectedLevel.equals("Level3")) {
            phantoms = new Enemy[8];
            phantoms[0] = new Enemy(4 * 36, 2*36, level1);
            phantoms[1] = new Enemy(10 * 36, 4 * 36, level1);
            phantoms[2] = new Enemy(4 * 36, 8 * 36, level1);
            phantoms[3] = new Enemy( 15* 36, 10* 36, level1);
            phantoms[4] = new Enemy(3 * 36, 15* 36, level1);
            phantoms[5] = new Enemy(15 * 36, 16* 36, level1);
            phantoms[6] = new Enemy(3 * 36, 15* 36, level1);
            phantoms[7] = new Enemy(7 * 36, 21* 36, level1);
        } else if (selectedLevel.equals("Level4")) {
            phantoms = new Enemy[8];
            phantoms[0] = new Enemy(5 * 36, 2*36, level1);
            phantoms[1] = new Enemy(16 * 36, 4 * 36, level1);
            phantoms[2] = new Enemy(3 * 36, 7 * 36, level1);
            phantoms[3] = new Enemy( 13* 36, 9* 36, level1);
            phantoms[4] = new Enemy(22 * 36, 9* 36, level1);
            phantoms[5] = new Enemy(3 * 36, 13* 36, level1);
            phantoms[6] = new Enemy(16 * 36, 15* 36, level1);
            phantoms[7] = new Enemy(23 * 36, 18* 36, level1);
        } else if (selectedLevel.equals("Level5")) {
            phantoms = new Enemy[8];
            phantoms[0] = new Enemy(6 * 36, 3*36, level1);
            phantoms[1] = new Enemy(16 * 36, 3 * 36, level1);
            phantoms[2] = new Enemy(5 * 36, 6 * 36, level1);
            phantoms[3] = new Enemy( 14* 36, 7* 36, level1);
            phantoms[4] = new Enemy(1 * 36, 11* 36, level1);
            phantoms[5] = new Enemy(16 * 36, 15* 36, level1);
            phantoms[6] = new Enemy(12 * 36, 16* 36, level1);
            phantoms[7] = new Enemy(4 * 36, 18* 36, level1);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        // calls drawLevel and gives parameter g2D to draw the level
        drawLevel(g2D);
        //draws pacman at pos 36,36
        if (singlePlayer){
            g2D.drawImage(player1.getPacman(), player1.getX(), player1.getY(), this);
        }
        if (!singlePlayer){
            if (player1.lives !=0){
                g2D.drawImage(player1.getPacman(), player1.getX(), player1.getY(), this);
            }

            if (player2.lives !=0){
                g2D.drawImage(player2.getPacman(), player2.getX(), player2.getY(), this);
            }
        }

        for (Enemy enemy: phantoms){
            if (!enemy.defeated) {
                g2D.drawImage(enemy.enemyImage, enemy.getX(), enemy.getY(), this);
            }
        }

    }

    public void drawLevel(Graphics g2D) {
        int r = level1.length;
        int c = level1[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (level1[i][j] == 1){
                    // draws a block of 35 pixels at position j*36,i*36
                    g2D.drawImage(wall, j* 36, i*36, this);
                } else if (level1[i][j] == 7) {
                    // draws the powerups.
                    // I added 9 to the positions cus a powerup is only 18 pixels, for it to be in the middle of the cell(a cell being 36x36 pixels).
                    g2D.drawImage(powerup, (j* 36)+9, (i*36)+9, this);
                } else if (level1[i][j] == 0 || level1[i][j] == 8) {
                    // draws eatables.
                    // same story as for the powerups. in this case an eatable object is only 9 pixels in size, so I added 14.
                    g2D.drawImage(eatable, (j* 36)+14, (i*36)+14, this);
                }
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        /*
        Moves player and enemies.
         */
        if (singlePlayer){
            player1.move(keyInput.player1Key);

        }
        if (!singlePlayer){
            player1.move(keyInput.player1Key);
            player2.move(keyInput.player2Key);
        }

        for (Enemy enemy: phantoms){
            enemy.move();
        }
        /*
        Checks the undefeatable status if enemy and player collide. And proceeds respectively.
         */
        if (singlePlayer){
            for (Enemy phantom: phantoms) {
                if (player1.undefeatable && !player1.killedEnemies.contains(phantom)) {
                    phantom.enemyImage = phantom.enemyScared;

                } else {
                    phantom.enemyImage = phantom.enemyHappy;

                }
            }
            for (Enemy phantom: phantoms) {
                if (player1.getX() / 36 == phantom.getX() / 36 && player1.getY() / 36 == phantom.getY() / 36 && player1.undefeatable && !player1.killedEnemies.contains(phantom)) {
                    phantom.defeat();
                    phantom.resetCoordinates();
                    player1.killedEnemies.add(phantom);
                } else if (player1.getX() / 36 == phantom.getX() / 36 && player1.getY() / 36 == phantom.getY() / 36) {
//                player1.setXY(36 * 5, 13 * 36);
                    if (selectedLevel.equals("Level1")) {
                        player1.setXY(36 * 5, 13 * 36);
                    } else if (selectedLevel.equals("Level2")) {
                        player1.setXY(36 * 5, 9 * 36);
                    } else if (selectedLevel.equals("Level3")) {
                        player1.setXY(36 * 5, 19 * 36);
                    } else if (selectedLevel.equals("Level4")) {
                        player1.setXY(36 * 5, 11 * 36);
                    } else if (selectedLevel.equals("Level5")) {
                        player1.setXY(36 * 6, 12 * 36);
                    }
                    player1.stop();
                    keyInput.player1Key = "";
                    player1.decreaseLives();
                }
            }
        }
        if (!singlePlayer){

            for (Enemy phantom: phantoms) {
                if ((player2.undefeatable && !player2.killedEnemies.contains(phantom)) || (player1.undefeatable && !player1.killedEnemies.contains(phantom))) {
                    phantom.enemyImage = phantom.enemyScared;

                } else {
                    phantom.enemyImage = phantom.enemyHappy;

                }
            }
            for (Enemy phantom: phantoms) {
                if (player1.getX() / 36 == phantom.getX() / 36 && player1.getY() / 36 == phantom.getY() / 36 && player1.undefeatable && !player1.killedEnemies.contains(phantom)) {
                    phantom.defeat();
                    phantom.resetCoordinates();
                    player1.killedEnemies.add(phantom);
                } else if (player1.getX() / 36 == phantom.getX() / 36 && player1.getY() / 36 == phantom.getY() / 36) {
//                player1.setXY(36 * 5, 13 * 36);
                    if (selectedLevel.equals("Level1")) {
                        player1.setXY(36 * 7, 13 * 36);
                    } else if (selectedLevel.equals("Level2")) {
                        player1.setXY(36 * 7, 9 * 36);
                    } else if (selectedLevel.equals("Level3")) {
                        player1.setXY(36 * 7, 19 * 36);
                    } else if (selectedLevel.equals("Level4")) {
                        player1.setXY(36 * 7, 11 * 36);
                    } else if (selectedLevel.equals("Level5")) {
                        player1.setXY(36 * 8, 12 * 36);
                    }
                    player1.stop();
                    keyInput.player1Key = "";
                    player1.decreaseLives();
                }

                if (player2.getX() / 36 == phantom.getX() / 36 && player2.getY() / 36 == phantom.getY() / 36 && player2.undefeatable && !player2.killedEnemies.contains(phantom)) {
                    phantom.defeat();
                    phantom.resetCoordinates();
                    player2.killedEnemies.add(phantom);
                } else if (player2.getX() / 36 == phantom.getX() / 36 && player2.getY() / 36 == phantom.getY() / 36) {
//                player2.setXY(7 * 36, 13 * 36);
                    if (selectedLevel.equals("Level1")) {
                        player2.setXY(5 * 36, 13 * 36);
                    } else if (selectedLevel.equals("Level2")) {
                        player2.setXY(5 * 36, 9 * 36);
                    } else if (selectedLevel.equals("Level3")) {
                        player2.setXY(5 * 36, 19 * 36);
                    } else if (selectedLevel.equals("Level4")) {
                        player2.setXY(5 * 36, 11 * 36);
                    } else if (selectedLevel.equals("Level5")) {
                        player2.setXY(6 * 36, 12 * 36);
                    }
                    player2.stop();
                    keyInput.player2Key = "";
                    player2.decreaseLives();
                }
            }
        }

        /*
        checks conditions to end the round.
         */

        gameOver = hasWon();
        //if there are no more lives
        if (!singlePlayer){
            if (player1.lives + player2.lives == 0){
                gameOver = true;
            }

        }else {
            if (player1.lives == 0) {
                gameOver = true;
            }

        }

        if (gameOver){
            int score;
            if (singlePlayer){
                score = player1.score;
            }else{
                score = player1.score + player2.score;
            }
            EndGame endGame = new EndGame(score);
            endGame.setVisible(true);
            gamePitch.setVisible(false);
            timer.stop();
            gamePitch.dispose();
        }

        fpsCounter += 0.5;

        if (fpsCounter == fps){
            fpsCounter = 0;
            repaint();
        }


    }
}
