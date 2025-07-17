package no.uib.inf101.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.controller.Controller;
import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.model.entities.Player;
import no.uib.inf101.model.powerups.AttackRangeBoost;
import no.uib.inf101.model.powerups.BecomeRanged;
import no.uib.inf101.model.powerups.PowerUp;
import no.uib.inf101.model.powerups.RandomPowerUpFactory;

/**
 * The main game model that manages the game state, player, monsters, and game logic.
 * Handles game updates, player movement, combat, power-ups, and wave progression.
 */
public class GameModel {
    /** Current movement direction of the player */
    Direction currDirection;
    /** Current attack direction of the player */
    Direction fightDirection;
    /** Current state of the game */
    Gamestate currGamestate;
    /** The player character */
    Player player;
    /** Width of the game map */
    public static int mapwidth = 1200;
    /** Height of the game map */
    public static int mapheight = 800;
    /** The game controller */
    Controller controller;
    /** Manager for monster waves */
    WaveManager waveManager;
    /** List of active monsters */
    ArrayList<Monster> monsters = new ArrayList<>();
    /** Counter for seconds elapsed */
    int secCounter = 0;
    /** Counter for frames elapsed */
    int fCounter = 0;
    /** Counter for game updates */
    int gameCounter = 0;
    /** Timer for escape key presses */
    int pressedescape = 0;
    /** Timer for space key presses */
    int pressedspace = 0;

    /** List of active power-ups */
    public ArrayList<PowerUp> powerUps;
    /** Factory for creating random power-ups */
    public RandomPowerUpFactory randomPowerUpFactory;
    /** Boolean for testing */
    private boolean isTest;
    /** Counter for attack animations */
    private int attackcounter = 0;
    /** Timer for swish attack animation */
    public int swishAttackTimer = 0;

    /**
     * Creates a new game model with the specified controller.
     * 
     * @param controller The game controller
     */
    public GameModel(Controller controller, boolean isTest) {
        powerUps = new ArrayList<>();
        this.controller = controller;
        this.isTest = isTest;
        waveManager = new WaveManager(this);
        currGamestate = Gamestate.WELCOMESCREEN;
        currDirection = Direction.NONE;
        fightDirection = Direction.UP;
        player = new Player(mapwidth/2 - 50 , mapheight/2 - 50);
        randomPowerUpFactory = new RandomPowerUpFactory(player, isTest);
    }

    /**
     * Gets the player character.
     * 
     * @return The player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Checks if the player's current position is within the map boundaries.
     * 
     * @return true if the player is in a legal position, false otherwise
     */
    private boolean isPlayerLegalPosition(){
        return player.getX() >= 0 && player.getX()+player.getEntitySize() <= mapwidth && player.getY() >= 0 && player.getY()+player.getEntitySize() <= mapheight;
    }

    /**
     * Moves the player based on the current direction.
     */
    private void movePlayer(){
        if (currDirection == Direction.UP) {
            player.move(0, -player.getSpeed());
        } else if (currDirection == Direction.DOWN) {
            player.move(0, player.getSpeed());
        } else if (currDirection == Direction.LEFT) {
            player.move(-player.getSpeed(), 0);
        } else if (currDirection == Direction.RIGHT) {
            player.move(player.getSpeed(), 0);
        }
    }

    /**
     * Checks for collisions between the player and monsters, applying damage if necessary.
     */
    private void gethitbymonsters(){
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (player.getBounds().intersects(monster.getBounds())) {
                player.takeDamage(monster.getAttackPower());
                if (player.getHealth() <= 0) {
                    currGamestate = Gamestate.GAMEOVER;
                }
            }
        }
    }

    /**
     * Moves a monster towards the player.
     * 
     * @param monster The monster to move
     */
    private void moveMonster(Monster monster){
        int dx = player.getX() - monster.getX();
        int dy = player.getY() - monster.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double a = dx / distance;
        double b = dy / distance;
        dx = (int) (a * monster.getSpeed());
        dy = (int) (b * monster.getSpeed());
        monster.move(dx, dy);
    }

    /**
     * Moves all monsters towards the player.
     */
    private void moveAllMonsters(){
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            moveMonster(monster);
        }
    }

    /**
     * Updates the player's movement and attack direction based on controller input.
     */
    public void updateDirection(){
        if (controller.upPressed) {
            currDirection = Direction.UP;
            fightDirection = Direction.UP;
        } else if (controller.downPressed) {
            currDirection = Direction.DOWN;
            fightDirection = Direction.DOWN;
        } else if (controller.leftPressed) {
            currDirection = Direction.LEFT;
            fightDirection = Direction.LEFT;
        } else if (controller.rightPressed) {
            currDirection = Direction.RIGHT;
            fightDirection = Direction.RIGHT;
        } else {
            currDirection = Direction.NONE;
        }
    }

    /**
     * Updates the game state based on controller input.
     */
    public void updateGamestate(){
        if (controller.escapePressed && currGamestate == Gamestate.ACTIVE && fCounter - pressedescape > 10) {
            currGamestate = Gamestate.PAUSE;
            pressedescape = fCounter;
        }
        else if (currGamestate == Gamestate.PAUSE && controller.escapePressed && fCounter - pressedescape > 10) {
            currGamestate = Gamestate.ACTIVE;
            pressedescape = fCounter;
        }
        else if (currGamestate == Gamestate.WELCOMESCREEN && controller.spacepressed){
            currGamestate = Gamestate.ACTIVE;
        }
        else if (controller.qpressed){
            currGamestate = Gamestate.QUIT;
        }
        else if (currGamestate != Gamestate.WELCOMESCREEN && currGamestate != Gamestate.QUIT && currGamestate != Gamestate.ACTIVE && controller.rpressed){
            restart();
        }
        if (controller.escapePressed){
            pressedescape = fCounter;
        }
        if (controller.kpressed){
            killAllMonsters();
        }
    }

    /**
     * Gets an iterator for the list of monsters.
     * 
     * @return An iterator for the monsters list
     */
    public Iterator<Monster> getMonstersIterator() {
        return monsters.iterator();
    }

    /**
     * Updates the player's position and handles attacks.
     */
    private void updatePlayer(){
        movePlayer();
        if (!isPlayerLegalPosition()){
            if (currDirection == Direction.UP) {
                player.move(0, player.getSpeed());
            } else if (currDirection == Direction.DOWN) {
                player.move(0, -player.getSpeed());
            } else if (currDirection == Direction.LEFT) {
                player.move(player.getSpeed(), 0);
            } else if (currDirection == Direction.RIGHT) {
                player.move(-player.getSpeed(), 0);
            }
        }
        if (controller.spacepressed && currGamestate == Gamestate.ACTIVE){
            swishAttack();
        }
    }

    /**
     * Updates the game state, handling player movement, monster movement, combat, and power-ups.
     */
    public void update(){
        if (currGamestate == Gamestate.ACTIVE){
            if (monsters.size() == 0) {
                PowerUp currPowerUp = getRandomPowerUp();
                powerUps.add(currPowerUp);
                monsters = waveManager.spawnMonsters(mapwidth, mapheight);
                if (getHighScore() < waveManager.getWaveNumber()){
                    setHighScore(waveManager.getWaveNumber());
                }
            }
            updateDirection();
            updatePlayer();
            moveAllMonsters();
            gethitbymonsters();
            activateAndRemovePowerups();
            gameCounter++;
            if (gameCounter%60 == 0) {
                secCounter++;
            }
        }
        fCounter++;
    }

    /**
     * Gets the current game state.
     * 
     * @return The current game state
     */
    public Gamestate getGamestate() {
        return currGamestate;
    }

    /**
     * Gets the current movement direction.
     * 
     * @return The current direction
     */
    public Direction getDirection() {
        return currDirection;
    }

    /**
     * Gets the map width.
     * 
     * @return The map width
     */
    public int getMapWidth() {
        return mapwidth;
    }

    /**
     * Gets the map height.
     * 
     * @return The map height
     */
    public int getMapHeight() {
        return mapheight;
    }

    /**
     * Attacks monsters within the player's attack area.
     */
    private void attackNearbyMonsters(){
        ArrayList<Monster> toberemoved = new ArrayList<>();
        Iterator<Monster> iterator = monsters.iterator();
        Rectangle attackArea = getDirectedPlayerAOE();
            while (iterator.hasNext()){
                Monster monster = iterator.next();
                if (attackArea.intersects(monster.getBounds())) {
                    monster.takeDamage(player.getAttackPower());
                    if (monster.getHealth() <= 0) {
                        toberemoved.add(monster);
                }
            }
        }
        for (Monster monster : toberemoved) {
            monsters.remove(monster);
        }
    }

    /**
     * Gets the wave manager.
     * 
     * @return The wave manager
     */
    public WaveManager getWaveManager() {
        return waveManager;
    }

    /**
     * Gets the seconds counter.
     * 
     * @return The seconds counter
     */
    public int getSecCounter() {
        return secCounter;
    }

    /**
     * Restarts the game, resetting all state.
     */
    private void restart(){
        currGamestate = Gamestate.ACTIVE;
        player = new Player(mapwidth/2 - 50 , mapheight/2 - 50);
        monsters.clear();
        waveManager = new WaveManager(this);
        gameCounter = 0;
        secCounter = 0;
        fCounter = 0;
        swishAttackTimer = 0;
        powerUps.clear();
        randomPowerUpFactory = new RandomPowerUpFactory(player, isTest);
    }

    /**
     * Gets the high score from the high score file.
     * 
     * @return The current high score
     */
    public int getHighScore(){
        File file = new File("/Users/kristianelmer/Documents/Skole/sem4/INF101/Kristian.Elmer_empty/src/main/java/no/uib/inf101/model/Highscore.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            if ((line = br.readLine()) != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the high score in the high score file.
     * 
     * @param score The new high score
     */
    public void setHighScore(int score){
        File file = new File("/Users/kristianelmer/Documents/Skole/sem4/INF101/Kristian.Elmer_empty/src/main/java/no/uib/inf101/model/Highscore.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a swish attack animation and damages monsters in range.
     */
    private void swishAttack(){
        if (gameCounter-(60/player.getAttackSpeed()) >= swishAttackTimer){
            swishAttackTimer = gameCounter;
            attackNearbyMonsters();
            attackcounter++;
        }
    }

    /**
     * Gets the player's current attack area of effect.
     * 
     * @return A rectangle representing the attack area
     */
    public Rectangle getDirectedPlayerAOE(){
        int attackRangeWidth = player.getAttackRangeWidth();
        int attackRangeLength = player.getAttackRangeLength();
        int size = player.getEntitySize();
        switch (fightDirection) {
            case LEFT:
                return new Rectangle(player.getX() - attackRangeLength, player.getY() - (attackRangeWidth-size)/2, attackRangeLength, attackRangeWidth);
            case RIGHT:
                return new Rectangle(player.getX() + size, player.getY() - (attackRangeWidth-size)/2, attackRangeLength, attackRangeWidth);
            case DOWN:
                return new Rectangle(player.getX() - (attackRangeWidth-size)/2, player.getY()+player.getEntitySize(), attackRangeWidth, attackRangeLength);
            case UP:
                return new Rectangle(player.getX() - (attackRangeWidth-size)/2, player.getY() - attackRangeLength, attackRangeWidth, attackRangeLength);
            default:
                return new Rectangle(player.getX(), player.getY(), size, size);
        }
    }

    /**
     * Gets the attack counter.
     * 
     * @return The attack counter
     */
    public int getAttackCounter(){
        return attackcounter;
    }

    /**
     * Gets the current fight direction.
     * 
     * @return The fight direction
     */
    public Direction getFightDirection(){
        return fightDirection;
    }

    /**
     * Gets the game counter.
     * 
     * @return The game counter
     */
    public int getGameCounter(){
        return gameCounter;
    }

    /**
     * Activates power-ups that the player has collected and removes them.
     */
    private void activateAndRemovePowerups(){
        Iterator<PowerUp> iterator = powerUps.iterator();
        ArrayList<PowerUp> removablePowerUps = new ArrayList<>();
        while (iterator.hasNext()){
            PowerUp powerUp = (PowerUp) iterator.next();
            if (powerUp.getBounds().intersects(player.getBounds())){
                powerUp.activate();
                removablePowerUps.add(powerUp);
            }
        }
        for (int i = 0; i < removablePowerUps.size(); i++){
            powerUps.remove(removablePowerUps.get(i));
        }
    }

    /**
     * Gets a random power-up from the factory.
     * 
     * @return A random power-up
     */
    private PowerUp getRandomPowerUp(){
        PowerUp currPowerUp = randomPowerUpFactory.getRandomPowerUp();
        if (player.getIsRanged() && (currPowerUp.getClass() == AttackRangeBoost.class || currPowerUp.getClass() == BecomeRanged.class)){
            return getRandomPowerUp();
        }
        else{
            return currPowerUp;
        }
    }

    /**
     * Removes all monsters from the game (debug function).
     */
    private void killAllMonsters(){
        monsters = new ArrayList<>();
    }
}
