package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class AttackRangeBoostTest {
    private AttackRangeBoost attackRangeBoost;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        attackRangeBoost = new AttackRangeBoost(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, attackRangeBoost.getX());
        assertEquals(200, attackRangeBoost.getY());
        assertEquals(30, attackRangeBoost.getSize());
        assertNotNull(attackRangeBoost.getBounds());
    }

    @Test
    void testActivate() {
        int initialWidth = player.getAttackRangeWidth();
        int initialLength = player.getAttackRangeLength();
        
        attackRangeBoost.activate();
        
        assertEquals(initialWidth, player.getAttackRangeWidth());
        assertEquals(initialLength + 50, player.getAttackRangeLength());
    }

    @Test
    void testMultipleActivations() {
        int initialLength = player.getAttackRangeLength();
        
        attackRangeBoost.activate();
        attackRangeBoost.activate();
        
        assertEquals(initialLength + 100, player.getAttackRangeLength());
    }
} 