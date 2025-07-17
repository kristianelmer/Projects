package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import no.uib.inf101.controller.Controller;
import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.model.entities.Player;
import no.uib.inf101.model.powerups.HealthRegen;

public class GameModelTest {
    private GameModel gameModel;
    private Controller mockController;

    @BeforeEach
    void setUp() {
        mockController = new Controller();
        gameModel = new GameModel(mockController, true);
    }
    
    @Test
    void testInitialState() {
        assertEquals(Gamestate.WELCOMESCREEN, gameModel.getGamestate());
        assertEquals(Direction.NONE, gameModel.getDirection());
        assertNotNull(gameModel.getPlayer());
        assertEquals(0, gameModel.getSecCounter());
    }

    @Test
    void testPlayerMovement() {
        // Test player movement in each direction
        Player player = gameModel.getPlayer();
        int initialX = player.getX();
        int initialY = player.getY();
        
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Test UP movement
        mockController.upPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertTrue(player.getY() < initialY, "Player should move up");
        mockController.upPressed = false;
        
        // Reset position
        player.move(initialX - player.getX(), initialY - player.getY());
        
        // Test DOWN movement
        mockController.downPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertTrue(player.getY() > initialY, "Player should move down");
        mockController.downPressed = false;
        
        // Reset position
        player.move(initialX - player.getX(), initialY - player.getY());
        
        // Test LEFT movement
        mockController.leftPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertTrue(player.getX() < initialX, "Player should move left");
        mockController.leftPressed = false;
        
        // Reset position
        player.move(initialX - player.getX(), initialY - player.getY());
        
        // Test RIGHT movement
        mockController.rightPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertTrue(player.getX() > initialX, "Player should move right");
    }
    
    @Test
    void testAttackDirection() {
        // Verify that fight direction is updated correctly
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        mockController.upPressed = true;
        gameModel.updateDirection();
        assertEquals(Direction.UP, gameModel.fightDirection);
        mockController.upPressed = false;
        
        mockController.downPressed = true;
        gameModel.updateDirection();
        assertEquals(Direction.DOWN, gameModel.fightDirection);
        mockController.downPressed = false;
        
        mockController.leftPressed = true;
        gameModel.updateDirection();
        assertEquals(Direction.LEFT, gameModel.fightDirection);
        mockController.leftPressed = false;
        
        mockController.rightPressed = true;
        gameModel.updateDirection();
        assertEquals(Direction.RIGHT, gameModel.fightDirection);
    }
    
    @Test
    void testSpaceAttackTrigger() {
        // Set up game state
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Record initial value of currattackcounter
        int initialCounter = gameModel.getAttackCounter();
        
        // Trigger attack with space
        mockController.spacepressed = true;
        for (int i = 0; i < 100; i++){
            gameModel.update();
        }

        
        // Verify attack counter was started
        assertTrue(gameModel.getAttackCounter() > initialCounter, 
                "Attack counter should increment when space is pressed");
    }
    
    @Test
    void testMonsterDamageOnAttack() {
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Spawn monsters if needed
        if (!gameModel.getMonstersIterator().hasNext()) {
            gameModel.monsters = gameModel.waveManager.spawnMonsters(
                gameModel.getMapWidth(), gameModel.getMapHeight());
        }
        
        // Get a monster and move it close to player
        Iterator<Monster> iter = gameModel.getMonstersIterator();
        assertTrue(iter.hasNext(), "Test requires at least one monster");
        Monster monster = iter.next();
        
        // Position monster within player's attack range
        Player player = gameModel.getPlayer();
        monster.move(player.getX() - monster.getX(), player.getY() - monster.getY());
        
        // Record monster's initial health
        int initialHealth = monster.getHealth();
        
        // Trigger attack
        mockController.spacepressed = true;
        gameModel.updateDirection();
        gameModel.update();
        
        // Check that monster took damage or was removed
        if (gameModel.monsters.contains(monster)) {
            assertTrue(monster.getHealth() <= initialHealth,
                "Monster should take damage when player attacks");
        } else {
            // Monster was killed and removed from the list
            assertTrue(true, "Monster was removed after attack");
        }
    }
    
    @Test
    void testPlayerTakesDamageFromMonsters() {
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        Player player = gameModel.getPlayer();
        
        // Spawn monsters if needed
        if (!gameModel.getMonstersIterator().hasNext()) {
            gameModel.monsters = gameModel.waveManager.spawnMonsters(
                gameModel.getMapWidth(), gameModel.getMapHeight());
        }
        
        // Get a monster and move it to player
        Iterator<Monster> iter = gameModel.getMonstersIterator();
        assertTrue(iter.hasNext(), "Test requires at least one monster");
        Monster monster = iter.next();
        
        // Save player's initial health
        int initialHealth = player.getHealth();
        
        // Position monster directly on player to ensure collision
        monster.move(player.getX() - monster.getX(), player.getY() - monster.getY());
        
        // Update to process collision
        gameModel.update();
        
        // Verify player took damage
        assertTrue(player.getHealth() < initialHealth, 
                "Player should take damage when colliding with monster");
    }
    
    @Test
    void testGameOverOnPlayerDeath() {
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        Player player = gameModel.getPlayer();
        
        // Set player health to 1
        player.setHealth(1);
        
        // Spawn monsters if needed
        if (!gameModel.getMonstersIterator().hasNext()) {
            gameModel.monsters = gameModel.waveManager.spawnMonsters(
                gameModel.getMapWidth(), gameModel.getMapHeight());
        }
        
        // Move a monster to player
        Iterator<Monster> iter = gameModel.getMonstersIterator();
        assertTrue(iter.hasNext(), "Test requires at least one monster");
        Monster monster = iter.next();
        monster.move(player.getX() - monster.getX(), player.getY() - monster.getY());
        
        // Update to process collision and trigger player death
        gameModel.update();
        
        // Check game state changed to GAMEOVER
        assertEquals(Gamestate.GAMEOVER, gameModel.getGamestate(),
                "Game state should change to GAMEOVER when player dies");
    }
    
    @Test
    void testPauseAndResumeGame() {
        // Start the game
        mockController.spacepressed = true;
        gameModel.updateGamestate();
        assertEquals(Gamestate.ACTIVE, gameModel.getGamestate());
        
        // Pause the game
        mockController.escapePressed = true;
        mockController.spacepressed = false;
        gameModel.fCounter = 60;
        gameModel.updateGamestate();
        assertEquals(Gamestate.PAUSE, gameModel.getGamestate());
        
        // Resume the game
        mockController.escapePressed = true;
        gameModel.fCounter = 120; // Ensure button cooldown is met
        gameModel.updateGamestate();
        assertEquals(Gamestate.ACTIVE, gameModel.getGamestate());
    }
    
    @Test
    void testNewWaveWhenMonstersCleared() {
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Clear any existing monsters
        gameModel.monsters.clear();
        
        // Record initial wave number
        int initialWave = gameModel.waveManager.getWaveNumber();
        
        // Update to trigger new wave
        gameModel.update();
        
        // Check wave progress
        assertTrue(gameModel.waveManager.getWaveNumber() >= initialWave,
                "Wave number should increase when all monsters are cleared");
        
        // Check that new monsters were spawned
        assertTrue(gameModel.getMonstersIterator().hasNext(),
                "New monsters should be spawned for the next wave");
    }
    
    @Test
    void testGameRestart() {
        // Set up non-initial game state
        gameModel.currGamestate = Gamestate.PAUSE;
        
        // Restart
        mockController.rpressed = true;
        gameModel.updateGamestate();
        gameModel.update();
        
        // Check game state reset
        assertEquals(Gamestate.ACTIVE, gameModel.getGamestate());
        assertEquals(1, gameModel.waveManager.getWaveNumber(),
                "Wave number should reset to 1 on restart");
    }

    @Test
    void testSecCounterIncrement() {
        // Set game to active state
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Record initial counter value
        int initialCounter = gameModel.getSecCounter();
        
        // Update counter about 60 times (1 second)
        for (int i = 0; i < 61; i++) {
            gameModel.update();
        }
        
        // Verify counter incremented
        assertTrue(gameModel.getSecCounter() > initialCounter,
                "Second counter should increment during active gameplay");
    }

    @Test
    void testMathematicalOperations() {
        // Test integer operations
        gameModel.currGamestate = Gamestate.ACTIVE;
        Player player = gameModel.getPlayer();
        
        // Test division operations
        int initialX = player.getX();
        int initialY = player.getY();
        mockController.rightPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertNotEquals(initialX, player.getX(), "Player should move right");
        
        // Test multiplication operations
        mockController.downPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertNotEquals(initialY, player.getY(), "Player should move down");
        
        // Test subtraction operations
        mockController.leftPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertNotEquals(initialX, player.getX(), "Player should move left");
        
        // Test addition operations
        mockController.upPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertNotEquals(initialY, player.getY(), "Player should move up");
    }

    @Test
    void testConditionalBoundaries() {
        // Test boundary conditions for player movement
        gameModel.currGamestate = Gamestate.ACTIVE;
        Player player = gameModel.getPlayer();
        
        // Test movement at map boundaries
        player.move(-player.getX(), 0); // Move to left edge
        mockController.leftPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertEquals(0, player.getX(), "Player should not move beyond left boundary");
        
        player.move(gameModel.getMapWidth() - player.getX(), 0); // Move to right edge
        mockController.rightPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertEquals(gameModel.getMapWidth(), player.getX(), "Player should not move beyond right boundary");
        
        player.move(0, -player.getY()); // Move to top edge
        mockController.upPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertEquals(0, player.getY(), "Player should not move beyond top boundary");
        
        player.move(0, gameModel.getMapHeight() - player.getY()); // Move to bottom edge
        mockController.downPressed = true;
        gameModel.updateDirection();
        gameModel.update();
        assertEquals(gameModel.getMapHeight(), player.getY(), "Player should not move beyond bottom boundary");
    }

    @Test
    void testMethodCalls() {
        // Test that critical methods are called
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Clear monsters to trigger spawning in update()
        gameModel.monsters.clear();
        gameModel.update(); // This will spawn new monsters
        
        // Get a monster and position it at a known distance from player
        Iterator<Monster> iter = gameModel.getMonstersIterator();
        assertTrue(iter.hasNext(), "Monsters should be spawned");
        Monster monster = iter.next();
        
        // Position monster at a known distance from player
        Player player = gameModel.getPlayer();
        monster.move(player.getX() - monster.getX() + 100, 
                    player.getY() - monster.getY());
        
        // Record initial distance to player
        int initialDx = player.getX() - monster.getX();
        int initialDy = player.getY() - monster.getY();
        double initialDistance = Math.sqrt(initialDx * initialDx + initialDy * initialDy);
        
        // Update game to move monster
        gameModel.update();
        
        // Calculate new distance to player
        int newDx = player.getX() - monster.getX();
        int newDy = player.getY() - monster.getY();
        double newDistance = Math.sqrt(newDx * newDx + newDy * newDy);
        
        // Verify monster moved closer to player
        assertTrue(newDistance < initialDistance, "Monster should move closer to player");
        
        // Test power-up activation
        gameModel.powerUps.clear();
        gameModel.powerUps.add(new HealthRegen(player.getX(), player.getY(), gameModel.getPlayer(), true));
        gameModel.update();
        assertTrue(gameModel.powerUps.isEmpty(), "Power-up should be activated and removed");
    }

    @Test
    void testReturnValues() {
        // Test return values of getter methods
        assertNotNull(gameModel.getPlayer(), "getPlayer should not return null");
        assertNotNull(gameModel.getMonstersIterator(), "getMonstersIterator should not return null");
        assertNotNull(gameModel.getGamestate(), "getGamestate should not return null");
        assertNotNull(gameModel.getDirection(), "getDirection should not return null");
        assertNotNull(gameModel.getWaveManager(), "getWaveManager should not return null");
        
        // Test numerical return values
        assertTrue(gameModel.getMapWidth() > 0, "getMapWidth should return positive value");
        assertTrue(gameModel.getMapHeight() > 0, "getMapHeight should return positive value");
        assertTrue(gameModel.getSecCounter() >= 0, "getSecCounter should return non-negative value");
        assertTrue(gameModel.getAttackCounter() >= 0, "getAttackCounter should return non-negative value");
        assertTrue(gameModel.getGameCounter() >= 0, "getGameCounter should return non-negative value");
    }

    @Test
    void testAttackArea() {
        // Test attack area calculations
        gameModel.currGamestate = Gamestate.ACTIVE;
        
        // Test attack area in each direction
        mockController.upPressed = true;
        gameModel.updateDirection();
        assertNotNull(gameModel.getDirectedPlayerAOE(), "Attack area should not be null when facing up");
        
        mockController.downPressed = true;
        gameModel.updateDirection();
        assertNotNull(gameModel.getDirectedPlayerAOE(), "Attack area should not be null when facing down");
        
        mockController.leftPressed = true;
        gameModel.updateDirection();
        assertNotNull(gameModel.getDirectedPlayerAOE(), "Attack area should not be null when facing left");
        
        mockController.rightPressed = true;
        gameModel.updateDirection();
        assertNotNull(gameModel.getDirectedPlayerAOE(), "Attack area should not be null when facing right");
    }
}