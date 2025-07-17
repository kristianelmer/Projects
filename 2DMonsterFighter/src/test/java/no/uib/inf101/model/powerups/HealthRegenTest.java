package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class HealthRegenTest {
    private HealthRegen healthRegen;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        healthRegen = new HealthRegen(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, healthRegen.getX());
        assertEquals(200, healthRegen.getY());
        assertEquals(30, healthRegen.getSize());
        assertNotNull(healthRegen.getBounds());
    }

    @Test
    void testActivate() {
        // Damage the player first
        player.takeDamage(50);
        int damagedHealth = player.getHealth();
        
        healthRegen.activate();
        
        assertEquals(player.getMaxhealth(), player.getHealth());
        assertTrue(player.getHealth() > damagedHealth);
    }

    @Test
    void testActivateAtFullHealth() {
        int initialHealth = player.getHealth();
        healthRegen.activate();
        assertEquals(initialHealth, player.getHealth());
    }

    @Test
    void testMultipleActivations() {
        player.takeDamage(50);
        healthRegen.activate();
        healthRegen.activate();
        assertEquals(player.getMaxhealth(), player.getHealth());
    }
} 