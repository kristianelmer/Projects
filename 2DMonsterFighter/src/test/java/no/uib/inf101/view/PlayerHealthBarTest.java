package no.uib.inf101.view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class PlayerHealthBarTest {
    private Player player;
    private PlayerHealthBar healthBar;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        healthBar = new PlayerHealthBar(player);
    }

    @Test
    void testInitialHealthBar() {
        assertEquals(300, healthBar.maxHealthBarLength);
        assertEquals(300, healthBar.greenLength);
        assertEquals(0, healthBar.redLength);
        assertEquals(300, healthBar.redStart);
        assertEquals(0, healthBar.greenStart);
    }

    @Test
    void testHealthBarAfterDamage() {
        player.takeDamage(50);
        healthBar = new PlayerHealthBar(player);
        
        assertEquals(150, healthBar.greenLength);
        assertEquals(150, healthBar.redLength);
        assertEquals(150, healthBar.redStart);
    }

    @Test
    void testHealthBarAtZeroHealth() {
        player.takeDamage(player.getMaxhealth());
        healthBar = new PlayerHealthBar(player);
        
        assertEquals(0, healthBar.greenLength);
        assertEquals(300, healthBar.redLength);
        assertEquals(0, healthBar.redStart);
    }

    @Test
    void testHealthBarAfterHealing() {
        // First take some damage
        player.takeDamage(50);
        healthBar = new PlayerHealthBar(player);
        
        // Then heal back to full
        player.setHealth(player.getMaxhealth());
        healthBar = new PlayerHealthBar(player);
        
        assertEquals(300, healthBar.greenLength);
        assertEquals(0, healthBar.redLength);
        assertEquals(300, healthBar.redStart);
    }
} 