package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class MaxHealthGainTest {
    private MaxHealthGain maxHealthGain;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        maxHealthGain = new MaxHealthGain(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, maxHealthGain.getX());
        assertEquals(200, maxHealthGain.getY());
        assertEquals(30, maxHealthGain.getSize());
        assertNotNull(maxHealthGain.getBounds());
    }

    @Test
    void testActivate() {
        int initialMaxHealth = player.getMaxhealth();
        int initialHealth = player.getHealth();
        
        maxHealthGain.activate();
        
        assertEquals(initialMaxHealth + 20, player.getMaxhealth());
        assertEquals(initialHealth, player.getHealth());
    }

    @Test
    void testMultipleActivations() {
        int initialMaxHealth = player.getMaxhealth();
        
        maxHealthGain.activate();
        maxHealthGain.activate();
        
        assertEquals(initialMaxHealth + 40, player.getMaxhealth());
    }
} 