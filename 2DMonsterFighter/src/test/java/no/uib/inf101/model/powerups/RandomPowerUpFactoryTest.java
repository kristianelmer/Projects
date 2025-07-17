package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;
import no.uib.inf101.model.GameModel;

public class RandomPowerUpFactoryTest {
    private RandomPowerUpFactory factory;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        factory = new RandomPowerUpFactory(player, true);
    }

    @Test   
    void testGetRandomPowerUp() {
        PowerUp powerUp = factory.getRandomPowerUp();
        assertNotNull(powerUp);
        assertTrue(powerUp instanceof PowerUp);
        
        // Check if the powerup is within map bounds
        assertTrue(powerUp.getX() >= 0 && powerUp.getX() <= GameModel.mapwidth);
        assertTrue(powerUp.getY() >= 0 && powerUp.getY() <= GameModel.mapheight);
    }

    @Test
    void testMultiplePowerUps() {
        // Test multiple powerup generations to ensure variety
        boolean hasAttackBoost = false;
        boolean hasAttackRangeBoost = false;
        boolean hasBecomeRanged = false;
        boolean hasHealthRegen = false;
        boolean hasMaxHealthGain = false;
        boolean hasSpeedBoost = false;
        boolean hasAttackSpeedBoost = false;

        // Generate multiple powerups to increase chance of getting all types
        for (int i = 0; i < 50; i++) {
            PowerUp powerUp = factory.getRandomPowerUp();
            if (powerUp instanceof AttackBoost) hasAttackBoost = true;
            else if (powerUp instanceof AttackRangeBoost) hasAttackRangeBoost = true;
            else if (powerUp instanceof BecomeRanged) hasBecomeRanged = true;
            else if (powerUp instanceof HealthRegen) hasHealthRegen = true;
            else if (powerUp instanceof MaxHealthGain) hasMaxHealthGain = true;
            else if (powerUp instanceof SpeedBoost) hasSpeedBoost = true;
            else if (powerUp instanceof AttackSpeedBoost) hasAttackSpeedBoost = true;
        }

        // Verify that all powerup types can be generated
        assertTrue(hasAttackBoost);
        assertTrue(hasAttackRangeBoost);
        assertTrue(hasBecomeRanged);
        assertTrue(hasHealthRegen);
        assertTrue(hasMaxHealthGain);
        assertTrue(hasSpeedBoost);
        assertTrue(hasAttackSpeedBoost);
    }

    @Test
    void testPowerUpActivation() {
        PowerUp powerUp = factory.getRandomPowerUp();
        assertDoesNotThrow(() -> powerUp.activate());
    }
} 