package no.uib.inf101.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.controller.Controller;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import no.uib.inf101.model.GameModel;
import no.uib.inf101.model.Gamestate;
import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.model.powerups.PowerUp;

/**
 * The main view component for the game.
 * Handles rendering of all game elements including the player, monsters, power-ups, and UI elements.
 * Implements Runnable for game loop functionality.
 */
public class GameView extends JPanel implements Runnable {
    /** The game model containing game state and logic */
    GameModel model;
    /** Player sprite images for different directions and animation frames */
    BufferedImage boyUp1Image;
    BufferedImage boyUp2Image;
    BufferedImage boyDown1Image;
    BufferedImage boyDown2Image;
    BufferedImage boyLeft1Image;
    BufferedImage boyLeft2Image;
    BufferedImage boyRight1Image;
    BufferedImage boyRight2Image;
    /** Monster sprite image */
    BufferedImage monsterImage;
    /** Game over screen image */
    BufferedImage gameoverImage;
    /** Pause screen image */
    BufferedImage pauseImage;
    /** Welcome screen image */
    BufferedImage welcomeImage;
    /** Weapon sprite images for different directions */
    BufferedImage swordUpImage;
    BufferedImage swordLeftImage;
    BufferedImage swordRightImage;
    BufferedImage swordDownImage;
    /** Ranged attack effect images */
    BufferedImage beamUpDownImage;
    BufferedImage beamLeftRightImage;
    /** Background tile image */
    BufferedImage sandTileImage;
    /** Boss monster sprite image */
    BufferedImage bossImage;

    /** Target frames per second for the game loop */
    int FPS = 60;
    /** Time per frame in milliseconds */
    int frameTime = 1000 / FPS;
    /** The game controller for handling input */
    Controller controller = new Controller();
    /** Thread for running the game loop */
    Thread gameThread;
    /** Counter for attack animations */
    int currattackcounter = 0;
    /** Duration of attack animation in frames */
    int attacktimer = 10;
    /** Flag indicating if an attack animation is in progress */
    boolean isAttacking = false;
    /** Counter for attack animation frames */
    int attacked = 0;
    /** Counter for walk animation frames */
    int walkcounter = 0;
    /** Counter for sprite animation frames */
    int spritecounter = 0;

    /**
     * Creates a new game view and initializes all game resources.
     * Loads all sprite images and sets up the game panel.
     */
    public GameView() {
        // get images
        try {
            boyUp1Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_up_1.png"));
            boyUp2Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_up_2.png"));
            boyDown1Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_down_1.png"));
            boyDown2Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_down_2.png"));
            boyLeft1Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_left_1.png"));
            boyLeft2Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_left_2.png"));
            boyRight1Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_right_1.png"));
            boyRight2Image = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boy_right_2.png"));
            monsterImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/monster.png"));
            gameoverImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/gameover.jpg"));
            pauseImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/pause.jpg"));
            welcomeImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/welcome.jpg"));
            swordUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/sword_up.png"));
            swordLeftImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/sword_left.png"));
            swordRightImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/sword_right.png"));
            swordDownImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/sword_down.png"));
            beamUpDownImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/beamupdown.png"));
            beamLeftRightImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/beamleftright.png"));
            sandTileImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/sand.png"));
            bossImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/boss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        model = new GameModel(controller, false);

        this.setPreferredSize(new Dimension(model.getMapWidth(),model.getMapHeight()));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
        
    }
    /**
     * Starts the game thread to begin the game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Paints the game components based on the current game state.
     * 
     * @param g The graphics context
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (model.getGamestate() == Gamestate.ACTIVE){
            drawGame(g2);
            model.updateGamestate();
        } 
        else if (model.getGamestate() == Gamestate.GAMEOVER){
            g2.drawImage(gameoverImage, 0, 0, model.getMapWidth(), model.getMapHeight(), null);
            model.updateGamestate();
        }
        else if (model.getGamestate() == Gamestate.PAUSE){
            g2.drawImage(pauseImage, 0, 0, model.getMapWidth(), model.getMapHeight(), null);
            model.updateGamestate();
        }
        else if (model.getGamestate() == Gamestate.WELCOMESCREEN){
            g2.drawImage(welcomeImage, 0, 0, model.getMapWidth(), model.getMapHeight(), null);
            model.updateGamestate();
        }
        else if (model.getGamestate() == Gamestate.QUIT){
            System.exit(0);
        }
        if (controller.spacepressed && model.getGamestate() == Gamestate.ACTIVE && model.getAttackCounter() > currattackcounter){
            // Can attack once every second/60 frames
            isAttacking = true;
            currattackcounter = model.getAttackCounter();
            attacked = 0;
        }
        if (controller.downPressed || controller.upPressed || controller.leftPressed || controller.rightPressed){
            walkcounter++;
        }
        
        if (isAttacking){
            // Show attack animation for 10 frames as attacktimer is set to 10
            attacked++;
            drawDummyAttackAnimation(g2);
            if (attacked > attacktimer){
                isAttacking = false;
            }
        }
        displayInfo(g2);
    }
    /**
     * Draws the main game elements including background, player, monsters, and UI.
     * 
     * @param g2 The graphics context
     */
    private void drawGame(Graphics2D g2){
        drawBackGroundTiles(g2);
        drawPlayerHealth(g2);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString(model.getPlayer().getHealth()+"/"+model.getPlayer().getMaxhealth(), 90,27);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Waves survived: " + model.getWaveManager().getWaveNumber(), model.getMapWidth()-186, model.getMapHeight()-60);
        g2.drawString("HighScore: " + model.getHighScore(),model.getMapWidth()-133 , model.getMapHeight()-30);
        g2.drawString("Time elapsed: " + model.getSecCounter(),model.getMapWidth()-180 , model.getMapHeight()-3);
        drawPlayer(g2);
        drawPowerUps(g2);
        Iterator<Monster> iterator = model.getMonstersIterator();

        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            BufferedImage currMonsterImage;
            if (model.getWaveManager().getIsBossActive()){
                currMonsterImage = bossImage;
            }
            else{
                currMonsterImage = monsterImage;
            }
            g2.drawImage(currMonsterImage, monster.getX(), monster.getY(), monster.getEntitySize(), monster.getEntitySize(), null);
            int maxHealthBarLength = monster.getEntitySize();
            double percentageHP = (double)monster.getHealth()/(double)monster.getMaxhealth();
            int healthbarlength = (int) (maxHealthBarLength*percentageHP);
            int redHealthBarLength = maxHealthBarLength-healthbarlength;
            int redHealthBarStart = monster.getX()+healthbarlength;
            g2.setColor(Color.green);
            g2.fillRect(monster.getX(), monster.getY()+monster.getEntitySize(), healthbarlength, 5);
            g2.setColor(Color.red);
            g2.fillRect(redHealthBarStart, monster.getY()+monster.getEntitySize(), redHealthBarLength, 5);
            }  
        }
    /**
     * Draws the player character with appropriate sprite based on direction and animation frame.
     * 
     * @param g2 The graphics context
     */
    private void drawPlayer(Graphics2D g2){
        if (walkcounter%10==0){
            spritecounter++;
            walkcounter++;
        }
        switch (model.getFightDirection()) {
            case UP:
            if (spritecounter%2 == 0){
                g2.drawImage(boyUp1Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            else{
                g2.drawImage(boyUp2Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            break;
            case DOWN:
            if (spritecounter%2 == 0){
                g2.drawImage(boyDown1Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            else{
                g2.drawImage(boyDown2Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            break;
            case LEFT:
            if (spritecounter%2 == 0){
                g2.drawImage(boyLeft1Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            else{
                g2.drawImage(boyLeft2Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            break;
            case RIGHT:
            if (spritecounter%2 == 0){
                g2.drawImage(boyRight1Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            else{
                g2.drawImage(boyRight2Image,model.getPlayer().getX(),model.getPlayer().getY(),model.getPlayer().getEntitySize(),model.getPlayer().getEntitySize(),null);
            }
            break;
            default:
                break;
        }
    }



    /**
     * The main game loop that updates the game state and repaints the screen.
     */
    @Override
    public void run() {
        while (gameThread != null) {
            double drawinterval = 1000000000 / FPS;
            double nextdrawtime = System.nanoTime() + drawinterval;
            model.update();
            repaint();
            try {
                double remainingtime = nextdrawtime - System.nanoTime();
                remainingtime /= 1000000;
                if (remainingtime < 0) {
                    remainingtime = 0;
                }
                Thread.sleep((long) remainingtime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    /**
     * Draws the attack animation based on the player's current attack direction.
     * 
     * @param g2 The graphics context
     */
    private void drawDummyAttackAnimation(Graphics2D g2){
        
        Rectangle AOE = model.getDirectedPlayerAOE();
        BufferedImage weaponUpImage = swordUpImage;
        BufferedImage weaponDownImage = swordDownImage;
        BufferedImage weaponLeftImage = swordLeftImage;
        BufferedImage weaponRightImage = swordRightImage;
        if (model.getPlayer().getIsRanged()){

            //TODO switch for beam animation

            weaponUpImage = beamUpDownImage;
            weaponDownImage = beamUpDownImage;
            weaponLeftImage = beamLeftRightImage;
            weaponRightImage = beamLeftRightImage;
            
        }
        switch (model.getFightDirection()) {
            case UP:
            g2.drawImage(weaponUpImage, AOE.x, AOE.y, AOE.width, AOE.height,null);
                break;
            case LEFT:
            g2.drawImage(weaponLeftImage, AOE.x, AOE.y, AOE.width, AOE.height,null);
                break;
            case RIGHT:
            g2.drawImage(weaponRightImage, AOE.x, AOE.y, AOE.width, AOE.height,null);
                break;
            case DOWN:
            g2.drawImage(weaponDownImage, AOE.x, AOE.y, AOE.width, AOE.height,null);
            default:
                break;
        }
    }
    /**
     * Draws all active power-ups on the screen.
     * 
     * @param g2 The graphics context
     */
    private void drawPowerUps(Graphics2D g2){
        ArrayList<PowerUp> powerUps = model.powerUps;
        for (PowerUp powerUp : powerUps){
            g2.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), powerUp.getSize(), powerUp.getSize(), null);
        }
    }
    /**
     * Draws the player's health bar.
     * 
     * @param g2 The graphics context
     */
    private void drawPlayerHealth(Graphics2D g2){
        PlayerHealthBar hp = new PlayerHealthBar(model.getPlayer());
        g2.setColor(Color.green);
        g2.fillRect(hp.greenStart, 0,(int) hp.greenLength, 30);
        g2.setColor(Color.red);
        g2.fillRect(hp.redStart, 0, hp.redLength, 30);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(hp.greenStart, 2,(int) hp.maxHealthBarLength, 30);
    }

    private void displayInfo(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        if (model.getGamestate() == Gamestate.WELCOMESCREEN){
            g2.drawString("Press SPACE to start game", model.getMapWidth()-800, model.getMapHeight()-200);
            g2.drawString("Controls are:", model.getMapWidth()-800, model.getMapHeight()-150);
            g2.drawString("ESC to pause, SPACE to attack and WASD/LeftRightUpDown to move", model.getMapWidth()-800, model.getMapHeight()-100);
        }
    }
    /**
     * Draws the background tiles.
     * 
     * @param g2 The graphics context
     */
    private void drawBackGroundTiles(Graphics2D g2){
        int tilesize = 48;
        int cols = model.getMapWidth()/tilesize;
        int rows = model.getMapHeight()/tilesize;
        for (int row = 0; row < rows+1; row++){
            for (int col = 0; col < cols; col++){
                g2.drawImage(sandTileImage, col*tilesize, row*tilesize, tilesize, tilesize, null);
            }
        }
    }
}