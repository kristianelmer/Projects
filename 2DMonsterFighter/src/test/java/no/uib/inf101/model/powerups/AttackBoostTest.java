package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class AttackBoostTest {
    private AttackBoost attackBoost;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        attackBoost = new AttackBoost(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, attackBoost.getX());
        assertEquals(200, attackBoost.getY());
        assertEquals(30, attackBoost.getSize());
        assertNotNull(attackBoost.getBounds());
    }

    @Test
    void testActivate() {
        int initialAttackPower = player.getAttackPower();
        attackBoost.activate();
        assertEquals(initialAttackPower + 50, player.getAttackPower());
    }

    @Test
    void testMultipleActivations() {
        int initialAttackPower = player.getAttackPower();
        attackBoost.activate();
        attackBoost.activate();
        assertEquals(initialAttackPower + 100, player.getAttackPower());
    }
} 