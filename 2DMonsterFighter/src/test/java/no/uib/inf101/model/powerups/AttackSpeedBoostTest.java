package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class AttackSpeedBoostTest {
    private AttackSpeedBoost attackSpeedBoost;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        attackSpeedBoost = new AttackSpeedBoost(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, attackSpeedBoost.getX());
        assertEquals(200, attackSpeedBoost.getY());
        assertEquals(30, attackSpeedBoost.getSize());
        assertNotNull(attackSpeedBoost.getBounds());
    }

    @Test
    void testActivate() {
        double initialSpeed = player.getAttackSpeed();
        
        attackSpeedBoost.activate();
        
        assertEquals(initialSpeed * 1.5, player.getAttackSpeed());
    }

    @Test
    void testMultipleActivations() {
        double initialSpeed = player.getAttackSpeed();
        
        attackSpeedBoost.activate();
        attackSpeedBoost.activate();
        
        assertEquals(initialSpeed * 1.5 * 1.5, player.getAttackSpeed());
    }
} 