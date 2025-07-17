package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class SpeedBoostTest {
    private SpeedBoost speedBoost;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        speedBoost = new SpeedBoost(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, speedBoost.getX());
        assertEquals(200, speedBoost.getY());
        assertEquals(30, speedBoost.getSize());
        assertNotNull(speedBoost.getBounds());
    }

    @Test
    void testActivate() {
        int initialSpeed = player.getSpeed();
        
        speedBoost.activate();
        
        assertEquals(initialSpeed + 4, player.getSpeed());
    }

    @Test
    void testMultipleActivations() {
        int initialSpeed = player.getSpeed();
        
        speedBoost.activate();
        speedBoost.activate();
        
        assertEquals(initialSpeed + 8, player.getSpeed());
    }
} 